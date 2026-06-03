package com.NovaStack.biblioteca.service.libraryItem;

import com.NovaStack.biblioteca.dto.libraryitem.LibraryItemResponseDTO;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import com.NovaStack.biblioteca.repository.LibraryItemRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryItemService {
    @Autowired
    LibraryItemRepository repository;

    @Autowired
    UserRepository userRepository;


    public List<LibraryItemResponseDTO> findAll() {

        User user = this.getUser();
        return repository.findByUser(user)
                .stream()
                .map(item -> new LibraryItemResponseDTO(
                        item.getId(),
                        item.getName(),
                        item.getAuthor(),
                        item.getReleaseDate(),
                        item.isBorrowed(),
                        item.getType()
                ))
                .toList();
    }

    public LibraryItemResponseDTO getItemById(Long id) {
        User user = this.getUser();
        LibraryItem item = repository.findByIdAndUser(id, user);
        return this.convertToResponse(item);
    }

    public List<LibraryItem> getItemsNotBorrewed() {
        User user = this.getUser();
        List<LibraryItem> libraryItems = repository.findByUserAndIsBorrowed(user, false);
        return libraryItems;
    }

    private LibraryItemResponseDTO convertToResponse(LibraryItem item){
        LibraryItemResponseDTO responseDTO = new LibraryItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getAuthor(),
                item.getReleaseDate(),
                item.isBorrowed(),
                item.getType()
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
