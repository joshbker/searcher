package dev.devous.searcher;

import org.jetbrains.annotations.NotNull;

public final class SearcherStrategy {
    SearcherStrategy() {
    }

    public double score(final @NotNull String a, final @NotNull String b) {
        String shorter;
        String longer;

        if (a.length() > b.length()) {
            shorter = b.toLowerCase();
            longer = a.toLowerCase();
        } else {
            shorter = a.toLowerCase();
            longer = b.toLowerCase();
        }

        int half = (shorter.length() / 2) + 1;
        String matchA = getMatching(a, b, half);
        String matchB = getMatching(b, a, half);

        if (matchA.length() == 0 || matchB.length() == 0 || matchA.length() != matchB.length()) {
            return 0.0D;
        }

        int transpositions = transpositions(matchA, matchB);
        double distance = (matchA.length() / ((double) shorter.length()) +
                matchB.length() / ((double) longer.length()) +
                (matchA.length() - transpositions) / ((double) matchA.length())) / 3.0;
        int common = commonPrefixLength(a, b);

        return distance + (0.1 * common * (1.0 - distance));
    }

    private @NotNull String getMatching(final @NotNull String a, final @NotNull String b, final int c) {
        StringBuilder common = new StringBuilder();
        StringBuilder copy = new StringBuilder(b);
        for (int i = 0; i < a.length(); i++) {
            char chr = a.charAt(i);
            boolean f = false;

            for (int j = Math.max(0, i - c); !f && j < Math.min(i + c, b.length()); j++) {
                if (copy.charAt(j) == chr) {
                    f = true;
                    common.append(chr);
                    copy.setCharAt(j, '*');
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
        return transpositions /= 2;
    }

    private int commonPrefixLength(final @NotNull String a, final @NotNull String b) {
        String shorter;
        String longer;

        if (a.length() > b.length()) {
            shorter = b.toLowerCase();
            longer = a.toLowerCase();
        } else {
            shorter = a.toLowerCase();
            longer = b.toLowerCase();
        }

        int result = 0;
        for (int i = 0; i < shorter.length(); i++) {
            if (shorter.charAt(i) != longer.charAt(i)) {
                break;
            }
            result++;
        }

        return Math.min(4, result);
    }
}