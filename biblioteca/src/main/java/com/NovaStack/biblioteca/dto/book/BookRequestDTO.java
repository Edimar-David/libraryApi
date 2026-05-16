package com.NovaStack.biblioteca.dto.book;

import java.time.LocalDate;


public record BookRequestDTO(
        String name,
        String author,
        String category,
        LocalDate releaseDate
) {
}
