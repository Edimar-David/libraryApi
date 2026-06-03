package com.NovaStack.biblioteca.dto.libraryitem;

import java.time.LocalDate;


public record BookRequestDTO(
        String name,
        String author,
        LocalDate releaseDate,
        String category
) {
}
