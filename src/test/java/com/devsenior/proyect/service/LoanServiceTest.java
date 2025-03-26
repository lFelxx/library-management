package com.devsenior.proyect.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.devsenior.proyect.exception.NotFoundException;
import com.devsenior.proyect.model.Book;
import com.devsenior.proyect.model.User;


public class LoanServiceTest {
    private BookService bookService;
    private UserService userService;
    private LoanService service;

    @BeforeEach
    void setup(){
        bookService = Mockito.mock(BookService.class);
        userService = Mockito.mock(UserService.class);
        service = new LoanService(bookService, userService);
    }

    @Test
    void testAddLoanWhenExistingUserAndExistingBook() throws Exception {
        //GIVEN
        var id = "5255";
        var isbn = "1536451";
        var mockUser = new User(id, "Luis", "luis@gmail.com");
        var mockBook = new Book(isbn, "Java", "Devsenior");

        Mockito.when(userService.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookService.getBookByIsbn(isbn)).thenReturn(mockBook);

        //WHEN
        service.addLoan(id, isbn);

        //THEN
        var loans = service.getLoans();
        assertEquals(1, loans.size());

        var loan = loans.get(0);
        assertNotNull(loan);
        assertNotNull(loan.getUser());
        assertNotNull(loan.getBook());
        assertEquals(loan.isBorrowed(),loan.isBorrowed());
    }


    @Test
    void testReturnBookWhenExistingLoan() throws Exception {
        //GIVEN
        var id = "1325";
        var isbn = "5245423";

        var userMock = new User(id, "luis", "luis@gmail.com");
        var bookMock = new Book(isbn, "Testing", "Devsenior");

        Mockito.when(userService.getUserById(id)).thenReturn(userMock);
        Mockito.when(bookService.getBookByIsbn(isbn)).thenReturn(bookMock);


        service.addLoan(id, isbn);
        //WHEN
        service.returnBook(id, isbn);

        //THEN
        var loans = service.getLoans();
        assertEquals(1, loans.size());

        var loan = loans.getFirst();
        assertEquals(id, loan.getUser().getId());
        assertEquals(isbn, loan.getBook().getIsbn());
        assertEquals(!loan.isBorrowed(), !loan.isBorrowed());
    }

    @Test
    void testAddLoanWhenNonExistingLoan() throws NotFoundException {
         //GIVEN
        var id = "1325";
        var isbn = "5245423";

        //WHEN - THEN
        assertThrows(NotFoundException.class, ()->{
            service.returnBook(id, isbn);
        });

    }

}
