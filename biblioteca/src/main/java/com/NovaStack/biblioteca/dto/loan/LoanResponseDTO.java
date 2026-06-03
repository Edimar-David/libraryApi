package com.NovaStack.biblioteca.dto.loan;

import com.NovaStack.biblioteca.model.enums.LoanStatus;

import java.time.LocalDate;

public record LoanResponseDTO(
        Long id,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate,
        LoanStatus loanStatus,
        String libraryItemName,
        String clientName
) {

}