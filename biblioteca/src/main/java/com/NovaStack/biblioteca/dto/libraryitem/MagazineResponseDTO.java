package com.NovaStack.biblioteca.dto.libraryitem;

import java.time.LocalDate;

public record MagazineResponseDTO(
        Long id,
        String name,
        String author,
        LocalDate releaseDate,
        Boolean isBorrowed,
        Integer editionNumber
) {
}
