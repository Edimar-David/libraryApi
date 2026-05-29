package com.NovaStack.biblioteca.service;

import com.NovaStack.biblioteca.dto.client.ClientRequestDTO;
import com.NovaStack.biblioteca.dto.client.ClientResponseDTO;
import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.enums.TypeClient;
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


    public ClientResponseDTO getClientById(Long id) {
        User user = this.getUser();
        Client client = clientRepository.findByIdAndUser(id, user);
        ClientResponseDTO response = this.convertToResponse(client);
        return response;
    }

    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
        User user = this.getUser();
        Client client = clientRepository.findByIdAndUser(id, user);

        client.setName(dto.name());
        client.setTypeClient(TypeClient.fromString(dto.typeClient()));
        client.setAcessCode(dto.acessCode());

        clientRepository.save(client);
       ClientResponseDTO response = this.convertToResponse(client);
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
