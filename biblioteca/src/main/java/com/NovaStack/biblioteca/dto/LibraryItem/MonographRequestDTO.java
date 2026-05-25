package com.NovaStack.biblioteca.dto.LibraryItem;

import java.time.LocalDate;

public record MonographRequestDTO(
        String name,
        String author,
        LocalDate releaseDate,
        String institution,
        String course
) {
}
