package com.NovaStack.biblioteca.dto.Loan;

import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.enums.LoanStatus;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import jakarta.persistence.*;

import java.time.LocalDate;

public record LoanRequestDTO(
        LocalDate loanDate,
        LocalDate dueDate,
        LoanStatus loanStatus,
        Long libraryItemID,
        Long clientID
){}

