package com.NovaStack.biblioteca.dto.libraryitem;

import java.time.LocalDate;

public record MediaRequestDTO(
        String name,
        String author,
        LocalDate releaseDate,
        String mediaFormat,
        Integer durationTime
) {
}
