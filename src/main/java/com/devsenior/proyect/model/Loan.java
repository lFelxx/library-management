package com.devsenior.proyect.model;

import java.time.LocalDate;

public class Loan {
    private User user;
    private Book book;
    private LocalDate loanDate;
    private boolean isBorrowed;

    public Loan(User user, Book book, LocalDate loanDate) {
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.isBorrowed = false;
    }


    public Loan(User user, Book book) {
        this(user, book, LocalDate.now());
        this.isBorrowed = false;
    }


    public User getUser() {
        return user;
    }


    public Book getBook() {
        return book;
    }


    public LocalDate getLoanDate() {
        return loanDate;
    }


    public boolean isBorrowed() {
        return isBorrowed;
    }


    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }


}
