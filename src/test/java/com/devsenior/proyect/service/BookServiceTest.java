package com.devsenior.proyect.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.proyect.exception.UnreturnedBookException;
import com.devsenior.proyect.model.Book;

public class BookServiceTest {

    private BookService service;

    @BeforeEach
    void setup(){
        service = new BookService();
    }

    @Test
    void testAddBook() throws UnreturnedBookException {
        //GIVEN
        var isbn = "250536";
        var title = "Modulo 3";
        var author = "Devsenior";

        //WHEN
        service.addBook(isbn, title, author);

        //THEN
        var book = service.getBookByIsbn(isbn);
        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
    }

    @Test
    void testDeleteExistingBook() throws NoSuchElementException {
        //GIVEN
        var isbn = "250536";
        var title = "Modulo 3";
        var author = "Devsenior";
        service.addBook(isbn, title, author);

        //WHEN
        service.deleteBook(isbn);

        //THEN
        try {
            service.getBookByIsbn(isbn);
            fail();
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    void testDeleteNonExistingBook() throws NoSuchElementException {
        //GIVEN
        var isbn = "250536";

        //WHEN - THEN
        assertThrows(NoSuchElementException.class, () ->{
            service.deleteBook(isbn);
        });
    }

    @Test
    void testDeleteBookWhenExistingBookButNotGivenIsbn(){
        //GIVEN
        service.addBook("25423", "Testing", "Devsenior");
        var isbn = "541641";
        //WHEN THEN
        assertThrows(NoSuchElementException.class, () -> {
            service.deleteBook(isbn);
        });

    }

    @Test
    void testGetAllBooks() {
        //GIVEN
        var isbn = "250536";
        var title = "Modulo 3";
        var author = "Devsenior";
        service.addBook(isbn, title, author);
        service.addBook(isbn, title, author);

        //WHEN
        List<Book> books = service.getAllBooks();

        //THEN
        assertNotNull(books);
        assertEquals(2, books.size());
        assertTrue(books.stream().anyMatch(book -> book.getIsbn().equals(isbn)));
    }

    @Test
    void testGetBookExistingByIsbn() {
        //GIVEN
        var isbn = "250536";
        var title = "Modulo 3";
        var author = "Devsenior";
        service.addBook(isbn, title, author);

        //WHEN
        var book = service.getBookByIsbn(isbn);

        //THEN
        assertNotNull(book);
        assertEquals(isbn, book.getIsbn());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
    }

    @Test
    void testGetBookNonExistingByIsbn() {
        //GIVEN
        var isbn = "250536";

        //WHEN - THEN
        assertThrows(NoSuchElementException.class, () ->{
            service.getBookByIsbn(isbn);
        });
    }

    @Test
    void testGetBookgByIsbnWhenWrongIsbn(){
        //GIVEN
        service.addBook("25452", "JavaFull", "Devsenior");
        var isbn = "6314756";

        //WHEN - THEN
        assertThrows(NoSuchElementException.class, () -> {
            service.getBookByIsbn(isbn);
        });


    }
}
