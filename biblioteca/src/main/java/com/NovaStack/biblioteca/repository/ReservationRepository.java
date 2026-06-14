package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.dto.Reservation.ReservationResponseDTO;
import com.NovaStack.biblioteca.model.Reservation;
import com.NovaStack.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
}
