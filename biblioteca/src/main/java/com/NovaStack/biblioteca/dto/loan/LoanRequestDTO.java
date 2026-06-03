package com.NovaStack.biblioteca.dto.loan;

import com.NovaStack.biblioteca.model.enums.LoanStatus;

import java.time.LocalDate;

public record LoanRequestDTO(
        LocalDate loanDate,
        LocalDate dueDate,
        LoanStatus loanStatus,
        Long libraryItemId,
        Long clientId
){}

