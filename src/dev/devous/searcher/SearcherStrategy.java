package dev.devous.searcher;

import org.jetbrains.annotations.NotNull;

public final class SearcherStrategy {
    private final double SCALING_FACTOR;
    private final int BIAS_PREFIX_LENGTH;

    {
        SCALING_FACTOR = 0.1D;
        BIAS_PREFIX_LENGTH = 4;
    }

    SearcherStrategy() {
    }

    public double score(final @NotNull String a, final @NotNull String b) {
        /* Assign the shorter & longer strings. */
        boolean aShortest = a.length() < b.length();
        String shorter = aShortest ? a : b;
        String longer = aShortest ? b : a;

        /* Calculate half of the length of the shorter string. */
        int halfLength = (shorter.length() / 2) + 1;

        /* Find the set of matching characters between the two strings. The set may vary depending on order of strings. */
        String matchA = getMatching(a, b, halfLength);
        String matchB = getMatching(b, a, halfLength);

        /* If at least one of the sets of common characters is empty, or not the same size, there is no similarity between the two strings. */
        if (matchA.length() == 0 || matchB.length() == 0 || matchA.length() != matchB.length()) {
            return 0.0D;
        }

        /* Calculate the number of transpositions between the two sets of common characters. */
        int transpositions = transpositions(matchA, matchB);

        /* Calculate the distance. */
        double distance = (matchA.length() / ((double) shorter.length()) +
                matchB.length() / ((double) longer.length()) +
                (matchA.length() - transpositions) / ((double) matchA.length())) / 3.0;

        /* Use prefix scale to give more favourable ratings to strings that match at the beginning up to MAX_PREFIX_LENGTH. */
        return distance + (SCALING_FACTOR * bias(a, b) * (1.0 - distance));
    }

    private @NotNull String getMatching(final @NotNull String a, final @NotNull String b, final int limit) {
        StringBuilder common = new StringBuilder();
        StringBuilder copy = new StringBuilder(b);
        for (int i = 0; i < a.length(); i++) {
            char chr = a.charAt(i);
            for (int j = Math.max(0, i - limit); j < Math.min(i + limit, b.length()); j++) {
                if (copy.charAt(j) == chr) {
                    common.append(chr);
                    copy.setCharAt(j, '*');
                    break;
                }
            }
        }

        return common.toString();
    }

    private int transpositions(final @NotNull String a, final @NotNull String b) {
        int transpositions = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                transpositions++;
            }
        }
        return transpositions / 2;
    }

    private int bias(final @NotNull String a, final @NotNull String b) {
        int result = 0;

        String[] a2 = a.toLowerCase().replaceAll("[^\\w ]", "")
                .replaceAll("[\\s]{2,}", " ").split("[\\s]");
        String[] b2 = b.toLowerCase().replaceAll("[^\\w ]", "")
                .replaceAll("[\\s]{2,}", " ").split("[\\s]");

        for (String a3 : a2) {
            for (String b3 : b2) {
                int len = a3.length() < BIAS_PREFIX_LENGTH || b3.length() < BIAS_PREFIX_LENGTH ?
                        Math.min(a3.length(), b3.length()) : BIAS_PREFIX_LENGTH;
                for (int i = 0; i < len; i++) {
                    if (a3.charAt(i) == b3.charAt(i)) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
