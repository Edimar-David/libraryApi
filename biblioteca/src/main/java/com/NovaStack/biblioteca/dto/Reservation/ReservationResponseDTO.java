package com.NovaStack.biblioteca.dto.Reservation;

import com.NovaStack.biblioteca.model.enums.ReservationStatus;

import java.time.LocalDate;

public record ReservationResponseDTO(
        Long id,
        LocalDate reservationDate,
        ReservationStatus reservationStatus,
        String libraryItem,
        String client

) {
}
