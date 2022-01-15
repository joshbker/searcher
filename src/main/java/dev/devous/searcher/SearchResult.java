package dev.devous.searcher;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final record SearchResult(@NotNull String result, double similarity, @NotNull String term,
                                 @NotNull List<String> in) {
    @Override
    public @NotNull String toString() {
        return "SearchResult{" +
                "result='" + result + '\'' +
                ", similarity=" + similarity +
                ", term='" + term + '\'' +
                ", in=" + in +
                '}';
    }
}
