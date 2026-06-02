package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.Loan;
import com.NovaStack.biblioteca.model.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRespository extends JpaRepository<Loan, Long>{
    boolean existsByClientAndStatusOrClientAndStatus(Client client, LoanStatus loanStatus, Client client1, LoanStatus loanStatus1);
}
