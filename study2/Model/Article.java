package com.example.study2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Article {
    @NotNull(message = "id cannot be null")
    private String id;
    @NotNull(message = "title cannot be null")
    @Size(max = 100,message = "maximum title 100 character ")
    private String title;
    @NotNull(message = "author cannot be null")
    @Size(min = 5,max = 20,message = "author from 5 to 20 characters")
    private String author;
    @NotNull(message = "content cannot be null")
    @Size(min = 201,message = "content must be more than 200 characters")
    private String content;
    @NotNull(message = "category cannot be null")
    @Pattern(regexp = "^(politics|sports|technology)$")
    private String category;
    @NotNull(message = "image url cannot be null")
    private String imageUrl;
    @AssertFalse(message = "isPublished should be false")
    private boolean isPublished;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}

