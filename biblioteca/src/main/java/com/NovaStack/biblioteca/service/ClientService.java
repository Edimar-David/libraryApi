package com.NovaStack.biblioteca.service;

import com.NovaStack.biblioteca.dto.client.ClientRequestDTO;
import com.NovaStack.biblioteca.dto.client.ClientResponseDTO;
import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.repository.ClientRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserRepository userRepository;

    public ClientResponseDTO createClient(ClientRequestDTO requestDTO) throws RuntimeException{
        User user = this.getUser();

        Client client = new Client(requestDTO, user);
        clientRepository.save(client);

        return this.convertToResponse(client);
    }



    public List<ClientResponseDTO> getAllClients() {
        User user = this.getUser();
        List<Client> clients = clientRepository.findByUser(user);

        List<ClientResponseDTO> response = clients.stream()
                .map(client -> new ClientResponseDTO(
                        client.getId(),
                        client.getName(),
                        client.getTypeClient(),
                        client.getReserveLimit()
                ))
                .collect(Collectors.toList());

        return response;
    }

    private ClientResponseDTO convertToResponse(Client client) {
        ClientResponseDTO responseDTO = new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getTypeClient(),
                client.getReserveLimit()
        );

        return responseDTO;
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
            throw new RuntimeException("User not found");
        }
    }
}
