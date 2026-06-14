package com.NovaStack.biblioteca.service;

import com.NovaStack.biblioteca.dto.Reservation.ReservationRequestDTO;
import com.NovaStack.biblioteca.dto.Reservation.ReservationResponseDTO;
import com.NovaStack.biblioteca.infra.exception.ResourceNotFoundException;
import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.Reservation;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import com.NovaStack.biblioteca.repository.ClientRepository;
import com.NovaStack.biblioteca.repository.LibraryItemRepository;
import com.NovaStack.biblioteca.repository.ReservationRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    LibraryItemRepository itemRepository;
    @Autowired
    ClientRepository clientRepository;

    public ReservationResponseDTO create(ReservationRequestDTO request) {
        User user = this.getUser();
        Client client = clientRepository.findByIdAndUser(request.clientId(), user);
        LibraryItem item = itemRepository.findByIdAndUser(request.libraryItemId(), user);
        Reservation reservation = new Reservation(
                request.reservationDate(),
                request.reservationStatus(),
                item,
                client,
                user
        );

        reservationRepository.save(reservation);

        return this.convertToResponse(reservation);
    }

    public List<ReservationResponseDTO> getAll() {
        User user = this.getUser();
        List<Reservation> reservations = reservationRepository.findByUser(user);
        List<ReservationResponseDTO> response = reservations.stream()
                .map(r -> new ReservationResponseDTO(
                        r.getId(),
                        r.getReservationDate(),
                        r.getStatusReservation(),
                        r.getLibraryItem().getName(),
                        r.getClient().getName()
                )).collect(Collectors.toList());
        return response;
    }

    private ReservationResponseDTO convertToResponse(Reservation reservation){
        ReservationResponseDTO response = new ReservationResponseDTO(
                reservation.getId(),
                reservation.getReservationDate(),
                reservation.getStatusReservation(),
                reservation.getLibraryItem().getName(),
                reservation.getClient().getName()
        );

        return response;
    }

    private User getUser() throws RuntimeException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new NullPointerException("email is null");
        }

        String userEmail = authentication.getName();
        Optional<User> user = userRepository.findUserByEmail(userEmail);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new ResourceNotFoundException("User not found");
        }
    }

}
