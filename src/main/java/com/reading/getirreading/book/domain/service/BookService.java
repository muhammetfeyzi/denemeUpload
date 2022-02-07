package com.reading.getirreading.book.domain.service;

import com.reading.getirreading.book.domain.model.Book;
import com.reading.getirreading.book.domain.model.UpdateBookStockRequest;
import com.reading.getirreading.book.domain.repository.BookRepository;
import com.reading.getirreading.exception.ErrorEnum;
import com.reading.getirreading.exception.ReadingApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;




@Slf4j
@Service
public class BookService {

    // We will use constructor injectuion causeof the best practices in spring boot
    private BookRepository bookRepository;
    public BookService (BookRepository bookRepository){
        this.bookRepository= bookRepository;
    }

    @Transactional
    public Book createBook(Book book) {
        if(book == null){
            throw new ReadingApiException(ErrorEnum.CONTENT_NOT_FOUND_ERROR);
        }
        log.info("Book name {} ,and id Creating {}", kv("book", book.getName()),kv("book",book.getId()));
        return bookRepository.save(book);

    }


    @Transactional
    public void updateBookStock(UpdateBookStockRequest request) {
        log.info("Book Service UpdateBookStock Request{}", kv("request", request));
        if (request.getId() != null) {
            Book dbBook = this.getBookById(request.getId());
            Integer differance = Math.toIntExact(dbBook.getStock() - request.getStock());
            if (differance > 0) {
                dbBook.setStock(differance);
                bookRepository.save(dbBook);
                log.info("Book Service UpdateBookStock  book{}, the new stock is {}", kv("request", request.getId(),differance.toString()));
            } else {
                throw new ReadingApiException(ErrorEnum.BOOK_NOT_AVAIBLE);
            }
        } else {
            throw new ReadingApiException(ErrorEnum.BOOK_ID_MUST_NOT_BLANK);
        }

    }

    public Book getBookById(Long id) {
        Optional<Book> dbProductEntity = bookRepository.findById(id);
        if (dbProductEntity.isPresent()) {
            log.info("One data fetch book id {}",kv("book", id));
            return dbProductEntity.get();
        } else {
            throw new ReadingApiException(ErrorEnum.BOOK_NOT_EXIST);
        }
    }

    public Page<Book> getAllBooks(int pageIndex, int pageSize) {
        log.info("getAllEvents {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));
        Page<Book> response = bookRepository.findAll(PageRequest.of(pageIndex, pageSize));
        return response;
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
        log.info("One data delete book id {}",kv("book", id));

    }

}