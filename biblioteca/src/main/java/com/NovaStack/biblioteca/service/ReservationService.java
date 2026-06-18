package com.NovaStack.biblioteca.service;

import com.NovaStack.biblioteca.dto.Reservation.ListReservationResponseDTO;
import com.NovaStack.biblioteca.dto.Reservation.ReservationRequestDTO;
import com.NovaStack.biblioteca.dto.Reservation.ReservationResponseDTO;
import com.NovaStack.biblioteca.infra.exception.BusinessException;
import com.NovaStack.biblioteca.infra.exception.ResourceNotFoundException;
import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.Reservation;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.enums.ReservationStatus;
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

    public ListReservationResponseDTO getAll() {
        User user = this.getUser();
        List<Reservation> reservations = reservationRepository.findByUserOrderByReservationDate(user);
        List<ReservationResponseDTO> reservationResponseDTOS = this.updateReservation(reservations);
        ListReservationResponseDTO response = new ListReservationResponseDTO(reservationResponseDTOS.stream()
                .filter(r -> r.reservationStatus().equals(ReservationStatus.AVAILABLE))
                .toList(),

                reservationResponseDTOS.stream()
                .filter(r -> !r.reservationStatus().equals(ReservationStatus.AVAILABLE))
                .toList()
        );
        return response;
    }

    public ReservationResponseDTO completedReservation(Boolean completeReservation, Long id) {
        User user = this.getUser();
        Reservation reservation = reservationRepository.findByIdAndUser(id, user);

        if (reservation.getStatusReservation() != ReservationStatus.AVAILABLE){
            throw new BusinessException("Não é possível concluir ou cancelar uma reserva com status "
                    + reservation.getStatusReservation() + ".");
        }
        if (completeReservation){
            reservation.setStatusReservation(ReservationStatus.COMPLETED);
        }else{
            reservation.setStatusReservation(ReservationStatus.CANCELLED);
        }
        reservationRepository.save(reservation);
        return this.convertToResponse(reservation);
    }

    private List<ReservationResponseDTO> updateReservation(List<Reservation> reservations){
        reservations.forEach(r -> {
    if(!r.getLibraryItem().isBorrowed()){
            r.setStatusReservation(ReservationStatus.AVAILABLE);
            reservationRepository.save(r);
        }
    });
        List<ReservationResponseDTO> response = reservations.stream()
                .map(r -> new ReservationResponseDTO(
                        r.getId(),
                        r.getReservationDate(),
                        r.getStatusReservation(),
                        r.getLibraryItem().getName(),
                        r.getClient().getName()
                )).toList();
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
