package com.NovaStack.biblioteca.dto.Reservation;

import com.NovaStack.biblioteca.model.enums.StatusReservation;

import java.time.LocalDate;

public record ReservationRequestDTO(
        LocalDate reservationDate,
        StatusReservation statusReservation,
        Long libraryItemId,
        Long clientId
) {
}
