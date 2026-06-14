package com.NovaStack.biblioteca.dto.Reservation;

import com.NovaStack.biblioteca.model.enums.StatusReservation;

import java.time.LocalDate;

public record ReservationResponseDTO(
        LocalDate reservationDate,
        StatusReservation statusReservation,
        String libraryItem,
        String client
) {
}
