package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.dto.client.ClientResponseDTO;
import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByUser(User user);

    Client findByIdAndUser(Long id, User user);

    List<Client> findByUserAndIsBanned(User user, boolean b);
}
