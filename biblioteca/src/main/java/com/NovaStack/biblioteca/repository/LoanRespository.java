package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.Loan;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRespository extends JpaRepository<Loan, Long>{
    boolean existsByClientAndLoanStatusOrClientAndLoanStatus(Client client, LoanStatus loanStatus, Client client1, LoanStatus loanStatus1);

    List<Loan> findAllByUser(User user);
}
