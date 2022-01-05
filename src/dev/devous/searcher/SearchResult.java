package dev.devous.searcher;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class SearchResult {
    private final @NotNull String result;
    private final double similarity;
    private final @NotNull String term;
    private final @NotNull String[] in;

    SearchResult(final @NotNull String result, final double similarity,
            final @NotNull String term, final @NotNull String[] in) {
        this.result = result;
        this.similarity = similarity;
        this.term = term;
        this.in = in;
    }

    public @NotNull String result() {
        return result;
    }

    public double similarity() {
        return similarity;
    }

    public @NotNull String term() {
        return term;
    }

    public @NotNull String[] in() {
        return in;
    }

    @Override
    public @NotNull String toString() {
        return "SearchResult{" +
                "result='" + result + '\'' +
                ", similarity=" + similarity +
                ", term='" + term + '\'' +
                ", in=" + Arrays.toString(in) +
                '}';
    }
}
