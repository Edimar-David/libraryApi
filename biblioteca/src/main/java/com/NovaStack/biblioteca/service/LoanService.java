package com.NovaStack.biblioteca.service;

import com.NovaStack.biblioteca.dto.Loan.LoanRequestDTO;
import com.NovaStack.biblioteca.dto.Loan.LoanResponseDTO;
import com.NovaStack.biblioteca.model.Loan;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.repository.LoanRespository;
import com.NovaStack.biblioteca.repository.UserRepository;
import com.NovaStack.biblioteca.service.libraryItem.LibraryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoanRespository respository;

    @Autowired
    LibraryItemService itemService;
    @Autowired
    ClientService clientService;

    public LoanResponseDTO createLoan(LoanRequestDTO request) {
        User user = this.getUser();

        if(request.libraryItem().isBorrowed()) {
            throw new IllegalStateException("Item já está emprestado");
        }

            Loan loan = new Loan(
                    request.loanDate(),
                    request.dueDate(),
                    request.loanStatus(),
                    request.libraryItem(),
                    request.client(),
                    user
            );

        return null;
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
