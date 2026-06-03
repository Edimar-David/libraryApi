package com.NovaStack.biblioteca.service.libraryItem;

import com.NovaStack.biblioteca.dto.libraryitem.MediaRequestDTO;
import com.NovaStack.biblioteca.dto.libraryitem.MediaResponseDTO;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.libraryItem.Media;
import com.NovaStack.biblioteca.repository.LibraryItemRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    LibraryItemRepository libraryItemRepository;
    @Autowired
    UserRepository userRepository;


    public MediaResponseDTO createMedia(MediaRequestDTO requestDTO) throws RuntimeException{
        User user = this.getUser();

        Media media = new Media(
                requestDTO.name(),
                requestDTO.author(),
                requestDTO.releaseDate(),
                user,
                requestDTO.mediaFormat(),
                requestDTO.durationTime()
        );
        libraryItemRepository.save(media);

        return this.convertToResponse(media);
    }



    private MediaResponseDTO convertToResponse(Media media){
        MediaResponseDTO responseDTO = new MediaResponseDTO(
                media.getId(),
                media.getName(),
                media.getAuthor(),
                media.getReleaseDate(),
                media.isBorrowed(),
                media.getMediaFormat(),
                media.getDurationTime()
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
