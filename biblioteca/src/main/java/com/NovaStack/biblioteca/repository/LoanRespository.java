package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRespository extends JpaRepository<Loan, Long>{
}
