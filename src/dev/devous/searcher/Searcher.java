package dev.devous.searcher;

import org.jetbrains.annotations.NotNull;

public final class Searcher {
    private static @NotNull Searcher INSTANCE;

    public static @NotNull Searcher instance() {
        return INSTANCE == null ? new Searcher() : INSTANCE;
    }

    private final @NotNull SearcherStrategy strategy;

    private Searcher() {
        strategy = new SearcherStrategy();
    }

    public @NotNull SearcherStrategy strategy() {
        return strategy;
    }

    public @NotNull SearchResult searchFor(final @NotNull String term, final @NotNull String[] in) {
        String result = "None";
        double similarity = Double.MIN_VALUE;
        for (String entry : in) {
            double score = strategy.score(term, entry);
            if (score >= similarity) {
                result = entry;
                similarity = score;
            }
        }
        return new SearchResult(result, similarity, term, in);
    }
}
