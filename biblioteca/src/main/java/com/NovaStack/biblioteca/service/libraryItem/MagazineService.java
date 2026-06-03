package com.NovaStack.biblioteca.service.libraryItem;

import com.NovaStack.biblioteca.dto.libraryitem.MagazineRequestDTO;
import com.NovaStack.biblioteca.dto.libraryitem.MagazineResponseDTO;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.libraryItem.Magazine;
import com.NovaStack.biblioteca.repository.LibraryItemRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MagazineService {

    @Autowired
    LibraryItemRepository libraryItemRepository;
    @Autowired
    UserRepository userRepository;


    public MagazineResponseDTO createMagazine(MagazineRequestDTO requestDTO) throws RuntimeException{
        User user = this.getUser();

        Magazine magazine = new Magazine(
                requestDTO.name(),
                requestDTO.author(),
                requestDTO.releaseDate(),
                user,
                requestDTO.editionNumber()
        );
        libraryItemRepository.save(magazine);

        return this.convertToResponse(magazine);
    }



    private MagazineResponseDTO convertToResponse(Magazine magazine){
        MagazineResponseDTO responseDTO = new MagazineResponseDTO(
                magazine.getId(),
                magazine.getName(),
                magazine.getAuthor(),
                magazine.getReleaseDate(),
                magazine.isBorrowed(),
                magazine.getEditionNumber()
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
