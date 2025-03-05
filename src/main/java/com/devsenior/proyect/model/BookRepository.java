package com.devsenior.proyect.model;

public interface BookRepository {

    void save(Book book);
    Book findByIsbn(String isbn);
}
