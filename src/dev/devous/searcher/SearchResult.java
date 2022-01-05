package dev.devous.searcher;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final record SearchResult(@NotNull String result, double similarity, @NotNull String term,
                                 @NotNull String[] in) {
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
