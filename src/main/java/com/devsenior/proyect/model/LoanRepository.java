package com.devsenior.proyect.model;

public interface LoanRepository {

    void save(Loan loan);
    Loan findById(String id);
}
