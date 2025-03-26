package com.devsenior.proyect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.devsenior.proyect.model.Book;

public class BookService {
    private List<Book> books;

    public BookService(){
        books = new ArrayList<>();
    }

    public void addBook(String isbn, String title, String author){
        books.add(new Book(isbn, title, author));
    }

    public List<Book> getAllBooks(){
        return books;
    }

    public Book getBookByIsbn(String isbn) throws NoSuchElementException{
        for (var book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new NoSuchElementException("El libro con isbn: " + isbn + " no fue encontrado");
    }

    public void deleteBook(String isbn) throws NoSuchElementException{
        for (var book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                return;
            }
        }
        throw new NoSuchElementException("El libro con isbn: " + isbn + " no se pudo eliminar");
    }
}
