package dev.devous.searcher;

public final class Searcher {
    private static Searcher INSTANCE;

    public static Searcher instance() {
        return INSTANCE == null ? new Searcher() : INSTANCE;
    }

    private final SearcherStrategy strategy;

    private Searcher() {
        strategy = new SearcherStrategy();
    }

    public SearcherStrategy strategy() {
        return strategy;
    }

    public SearchResult searchFor(final String term, final String[] in) {
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
