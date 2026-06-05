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
import com.NovaStack.biblioteca.repository.LoanRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LibraryItemRepository itemRepository;
    @Autowired
    ClientRepository clientRepository;

    public LoanResponseDTO createLoan(LoanRequestDTO request) {
        User user = this.getUser();

        if (request.loanDate().isAfter(request.dueDate())){
            throw new BusinessException("A data de devolução não pode ser anterior à data do empréstimo");
        }

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

        if(request.loanStatus().equals(LoanStatus.IN_PROGRESS)) {
            if (request.returnDate() == null) {
                throw new BusinessException("emprestimos finalizados precisam de uma data de retorno");
            }else if(request.returnDate().isBefore(request.loanDate())){
                throw new BusinessException("A data de retorno deve ser superior a data de criação");
            }
        }


        boolean hasActiveLoan = loanRepository.existsByClientAndLoanStatusOrClientAndLoanStatus(
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

        loanRepository.save(loan);
        item.setBorrowed(true);
        itemRepository.save(item);

        return this.convertToResponse(loan);
    }

    public List<LoanResponseDTO> getAll(){
        User user = this.getUser();
        List<Loan> loans = loanRepository.findAllByUser(user);
        this.updateOverdueLoanStatus(loans);
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

    private List<Loan> updateOverdueLoanStatus(List<Loan> loans){
        LocalDate date = LocalDate.now();
        loans.stream()
                .filter(l -> l.getLoanStatus().equals(LoanStatus.IN_PROGRESS))
                .filter(l -> l.getDueDate().isBefore(date))
                .forEach(l -> {
                    l.setLoanStatus(LoanStatus.OVERDUE);
                    this.loanRepository.save(l);
                });

        return loans;
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