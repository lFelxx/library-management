package com.devsenior.proyect.service;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.proyect.exception.NotFoundException;
import com.devsenior.proyect.exception.UnreturnedBookException;
import com.devsenior.proyect.model.Loan;

public class LoanService {
    private List<Loan> loans;
    private BookService bookService;
    private UserService userService;


    public LoanService(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loans = new ArrayList<>();
    }

    public void addLoan(String id, String isbn) throws Exception{
        var user = userService.getUserById(id);
        var book = bookService.getBookByIsbn(isbn);

        for (var loan : loans) {
            if (loan.getBook().getIsbn().equals(isbn)
                && loan.isBorrowed()) {
                throw new UnreturnedBookException("El libro con isbn: " + " ya se encuentra prestado");
            }
        }
        var loan = (new Loan(user, book));
        loans.add(loan);
        loan.setBorrowed(true);
    }

    public void returnBook(String id, String isbn) throws NotFoundException{
        for (var loan : loans) {
            if (loan.getUser().getId().equals(id)
                    && loan.getBook().getIsbn().equals(isbn)
                    && loan.isBorrowed()) {
                loan.setBorrowed(false);
                return;
            }
        }
        throw new NotFoundException("No hay un prestamo del libro: " + isbn + " para el usuario: " + id);
    }

    public List<Loan> getLoans(){
        return loans;
    }
}
