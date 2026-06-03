package com.NovaStack.biblioteca.service.libraryItem;

import com.NovaStack.biblioteca.dto.libraryitem.MonographRequestDTO;
import com.NovaStack.biblioteca.dto.libraryitem.MonographResponseDTO;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.libraryItem.Monograph;
import com.NovaStack.biblioteca.repository.LibraryItemRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonographService {

    @Autowired
    LibraryItemRepository libraryItemRepository;
    @Autowired
    UserRepository userRepository;


    public MonographResponseDTO createMonograph(MonographRequestDTO requestDTO) throws RuntimeException{
        User user = this.getUser();

        Monograph monograph = new Monograph(
                requestDTO.name(),
                requestDTO.author(),
                requestDTO.releaseDate(),
                user,
                requestDTO.institution(),
                requestDTO.course()
        );
        libraryItemRepository.save(monograph);

        return this.convertToResponse(monograph);
    }



    private MonographResponseDTO convertToResponse(Monograph monograph){
        MonographResponseDTO responseDTO = new MonographResponseDTO(
                monograph.getId(),
                monograph.getName(),
                monograph.getAuthor(),
                monograph.getReleaseDate(),
                monograph.isBorrowed(),
                monograph.getInstitution(),
                monograph.getCourse()
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
