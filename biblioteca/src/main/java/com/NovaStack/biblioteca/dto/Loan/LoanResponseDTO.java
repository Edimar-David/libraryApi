package com.NovaStack.biblioteca.dto.Loan;

import com.NovaStack.biblioteca.model.Client;
import com.NovaStack.biblioteca.model.enums.LoanStatus;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;

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