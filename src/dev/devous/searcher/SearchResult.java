package dev.devous.searcher;

import java.util.Arrays;

public final class SearchResult {
    private final String result;
    private final double similarity;
    private final String term;
    private final String[] in;

    SearchResult(final String result, final double similarity,
            final String term, final String[] in) {
        this.result = result;
        this.similarity = similarity;
        this.term = term;
        this.in = in;
    }

    public String result() {
        return result;
    }

    public double similarity() {
        return similarity;
    }

    public String term() {
        return term;
    }

    public String[] in() {
        return in;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "result='" + result + '\'' +
                ", similarity=" + similarity +
                ", term='" + term + '\'' +
                ", in=" + Arrays.toString(in) +
                '}';
    }
}
