package com.NovaStack.biblioteca.service;

import com.NovaStack.biblioteca.dto.loan.LoanRequestDTO;
import com.NovaStack.biblioteca.dto.loan.LoanResponseDTO;
import com.NovaStack.biblioteca.infra.exception.BusinessException;
import com.NovaStack.biblioteca.infra.exception.ResourceNotFoundException;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        LibraryItem item = itemRepository.findByIdAndUser(request.libraryItemId(), user);
        if (item == null){
            throw new ResourceNotFoundException("item not found");
        }
        if(item.isBorrowed()){
            throw new BusinessException("Item já está em emprestimo");
        }

        Client client = clientRepository.findByIdAndUser(request.clientId(), user);
        if (client == null){
            throw new ResourceNotFoundException("client not found");
        }
        if(client.isBanned()){
            throw new BusinessException("cliente banido");
        }


        boolean hasActiveLoan = loanRespository.existsByClientAndLoanStatusOrClientAndLoanStatus(
                client,
                LoanStatus.IN_PROGRESS,
                client,
                LoanStatus.OVERDUE
        );
        if (hasActiveLoan) {
            throw new BusinessException("Cliente já possui um empréstimo ativo");
        }


        Loan loan = new Loan(
                request.loanDate(),
                request.dueDate(),
                request.loanStatus(),
                item,
                client,
                user
        );

        loanRespository.save(loan);
        item.setBorrowed(true);
        itemRepository.save(item);

        return this.convertToResponse(loan);
    }

    public List<LoanResponseDTO> getAll(){
        User user = this.getUser();
        List<Loan> loans = loanRespository.findAllByUser(user);

        List<LoanResponseDTO> response = loans.stream()
                .map(l -> new LoanResponseDTO(
                        l.getId(),
                        l.getLoanDate(),
                        l.getDueDate(),
                        l.getReturnDate(),
                        l.getLoanStatus(),
                        l.getLibraryItem().getName(),
                        l.getClient().getName()
                )).collect(Collectors.toList());

        return response;
    }

    private LoanResponseDTO convertToResponse(Loan loan){
        LoanResponseDTO response = new LoanResponseDTO(
                loan.getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getLoanStatus(),
                loan.getLibraryItem().getName(),
                loan.getClient().getName()
        );

        return response;
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
            throw new ResourceNotFoundException("User not found");
        }
    }
}