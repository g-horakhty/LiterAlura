package com.literalura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResponse(
        @JsonAlias("count") Integer count,
        @JsonAlias("next") String next,
        @JsonAlias("previous") String previous,
        @JsonAlias("results") List<BookData> results
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record BookData(
            @JsonAlias("title") String title,
            @JsonAlias("authors") List<AuthorResponse> authors,
            @JsonAlias("languages") List<String> languages,
            @JsonAlias("download_count") Integer downloadCount
    ) {}
}