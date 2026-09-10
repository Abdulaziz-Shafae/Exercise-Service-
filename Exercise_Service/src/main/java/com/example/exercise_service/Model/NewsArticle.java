package com.example.exercise_service.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotBlank(message = "The id must not be blank")
    @NotEmpty(message = "The id must not be empty")
    private String ID;

    @NotBlank(message = "The title must not be blank")
    @NotEmpty(message = "The title must not be empty")
    @Size(max = 100, message = "the title must be less then 100 ")
    private String title;

    @NotBlank(message = "The title must not be blank")
    @NotEmpty(message = "The title must not be empty")
    @Size(min = 4, max = 20, message = "the title must be 4-20 length")
    private String author;

    @NotBlank(message = "The title must not be blank")
    @NotEmpty(message = "The title must not be empty")
    @Size(min = 200, message = "the title must be more then 200 ")
    private String content;

    @NotBlank(message = "The category must not be blank")
    @NotEmpty(message = "The category must not be empty")
    @Pattern(regexp = "^(politics|sports|technology)$",
            message = "The category must be politics or sports or technology")
    private String category;

    @NotBlank(message = "The image Url must not be blank")
    @NotEmpty(message = "The image Url must not be empty")
    private String imageUrl;

    private boolean isPublished;

    private LocalDate publishDate;

}
