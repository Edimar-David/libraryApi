package com.NovaStack.biblioteca.dto.Reservation;

import java.util.List;

public record ListReservationResponseDTO(
        List<ReservationResponseDTO> isAvailable,
        List<ReservationResponseDTO> notAvailable
) {

}
