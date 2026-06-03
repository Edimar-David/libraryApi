package com.NovaStack.biblioteca.dto.libraryitem;

import java.time.LocalDate;

public record MediaResponseDTO(
        Long id,
        String name,
        String author,
        LocalDate releaseDate,
        Boolean isBorrowed,
        String mediaFormat,
        Integer durationTime
) {
}
