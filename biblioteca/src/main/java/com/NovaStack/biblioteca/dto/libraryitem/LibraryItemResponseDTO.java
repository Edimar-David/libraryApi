package com.NovaStack.biblioteca.dto.libraryitem;

import java.time.LocalDate;

public record LibraryItemResponseDTO(
        Long id,
        String name,
        String author,
        LocalDate releaseDate,
        Boolean isBorrowed,
        String typeItem
) {
}
