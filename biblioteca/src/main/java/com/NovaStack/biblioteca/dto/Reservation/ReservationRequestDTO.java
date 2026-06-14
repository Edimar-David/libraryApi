package com.NovaStack.biblioteca.dto.Reservation;

import com.NovaStack.biblioteca.model.enums.ReservationStatus;

import java.time.LocalDate;

public record ReservationRequestDTO(
        LocalDate reservationDate,
        ReservationStatus reservationStatus,
        Long libraryItemId,
        Long clientId
) {
}
