package com.NovaStack.biblioteca.dto.libraryitem;

import java.time.LocalDate;

public record MonographResponseDTO(
        Long id,
        String name,
        String author,
        LocalDate releaseDate,
        Boolean isBorrowed,
        String institution,
        String course
) {
}
