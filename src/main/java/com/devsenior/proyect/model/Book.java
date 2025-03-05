package com.devsenior.proyect.model;

public class Book {

    private final String isbn;
    private String title;
    private String author;
    private boolean isBorrowed;



    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        isBorrowed = false;
    }



    public String getIsbn() {
        return isbn;
    }



    public String getTitle() {
        return title;
    }



    public String getAuthor() {
        return author;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public void setAuthor(String author) {
        this.author = author;
    }



    public boolean isBorrowed() {
        return isBorrowed;
    }



    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }


}
