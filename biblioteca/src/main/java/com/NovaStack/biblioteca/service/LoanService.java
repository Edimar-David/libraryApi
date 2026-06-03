package com.NovaStack.biblioteca.service;

import com.NovaStack.biblioteca.dto.loan.LoanRequestDTO;
import com.NovaStack.biblioteca.dto.loan.LoanResponseDTO;
import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.Loan;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.enums.LoanStatus;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import com.NovaStack.biblioteca.repository.ClientRepository;
import com.NovaStack.biblioteca.repository.LibraryItemRepository;
import com.NovaStack.biblioteca.repository.LoanRespository;
import com.NovaStack.biblioteca.repository.UserRepository;
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
    LoanRespository loanRespository;

    @Autowired
    LibraryItemRepository itemRepository;
    @Autowired
    ClientRepository clientRepository;

    public LoanResponseDTO createLoan(LoanRequestDTO request) {
        User user = this.getUser();
        LibraryItem item = itemRepository.findByIdAndUser(request.libraryitemid(), user);
        Client client = clientRepository.findByIdAndUser(request.clientid(), user);

        if(item.isBorrowed()) {
            throw new IllegalStateException("Item já está em emprestimo");
        }

        boolean hasActiveLoan = loanRespository.existsByClientAndLoanStatusOrClientAndLoanStatus(
                client,
                LoanStatus.IN_PROGRESS,
                client,
                LoanStatus.OVERDUE
        );

        if (hasActiveLoan) {
            throw new IllegalStateException("Cliente já possui um empréstimo ativo");
        }

        if(client.isBanned()){
            throw new IllegalStateException("cliente banido");
        }

            Loan loan = new Loan(
                    request.loanDate(),
                    request.dueDate(),
                    request.loanStatus(),
                    item,
                    client,
                    user
            );

        item.setBorrowed(true);
        itemRepository.save(item);

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