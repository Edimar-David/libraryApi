package com.NovaStack.biblioteca.service.libraryItem;

import com.NovaStack.biblioteca.dto.LibraryItem.LibraryItemResponseDTO;
import com.NovaStack.biblioteca.repository.LibraryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryItemService {
    @Autowired
    LibraryItemRepository repository;


    public List<LibraryItemResponseDTO> findAll() {

        return repository.findAll()
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
}
