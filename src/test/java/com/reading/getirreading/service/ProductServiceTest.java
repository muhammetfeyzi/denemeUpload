package com.reading.getirreading.service;

import com.reading.getirreading.book.domain.model.Book;
import com.reading.getirreading.book.domain.repository.BookRepository;
import com.reading.getirreading.book.domain.service.BookService;
import com.reading.getirreading.exception.ReadingApiException;
import com.reading.getirreading.utils.Utils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    //creating book success case . . .
    @Test
    public void createBook_shouldBeSuccess() {
        Book product = Utils.createBook();
        when(bookRepository.save(any(Book.class))).thenReturn(product);
        Book result = bookService.createBook(product);
        Assert.assertNotNull(result);
    }

    // creating book failure case
    @Test
    public void createBook_shouldBeError_productNull() {
        expectedException.expect(ReadingApiException.class);
        bookService.createBook(null);
    }

    // fetch book success case
    @Test
    public void getBookById_shouldBeSuccess() {
        Book book = Utils.createBook();
        when(bookRepository.findById(book.getId())).thenReturn(java.util.Optional.of(book));
        Book result = bookService.getBookById(book.getId());
        Assert.assertNotNull(result);
    }

    //fetch book failure case
    @Test
    public void getBookById_shouldBeError_productNotFound() {
        expectedException.expect(ReadingApiException.class);
        bookService.getBookById(2l);
    }
}