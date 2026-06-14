package com.NovaStack.biblioteca.model;

import com.NovaStack.biblioteca.model.enums.ReservationStatus;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDate reservationDate;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus = ReservationStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "library_item_id", nullable = false)
    LibraryItem libraryItem;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Reservation(LocalDate reservationDate, ReservationStatus reservationStatus, LibraryItem libraryItem, Client client, User user) {
        this.reservationDate = reservationDate;
        if(reservationStatus != null) {
            this.reservationStatus = reservationStatus;
        }
        this.libraryItem = libraryItem;
        this.client = client;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public ReservationStatus getStatusReservation() {
        return reservationStatus;
    }

    public void setStatusReservation(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    public void setLibraryItem(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
