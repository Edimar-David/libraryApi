package com.NovaStack.biblioteca.dto.client;

import com.NovaStack.biblioteca.model.enums.TypeClient;

public record ClientResponseDTO(
    Long id,
    String name,
    TypeClient typeClient,
    int limit
) {
}
