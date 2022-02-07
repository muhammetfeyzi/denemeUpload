package com.reading.getirreading.book.api.v1;

import com.reading.getirreading.book.domain.model.Book;
import com.reading.getirreading.book.domain.model.UpdateBookStockRequest;
import com.reading.getirreading.book.domain.service.BookService;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        return new ResponseEntity(bookService.createBook(book), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBook(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(bookService.getAllBooks(pageIndex, pageSize));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getFindBook(@RequestParam Long id ){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PutMapping
    public void updateBookStock(@RequestBody @Valid UpdateBookStockRequest updateBookStockRequest){
         bookService.updateBookStock(updateBookStockRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@RequestParam Long id){
        bookService.deleteBookById(id);
    }

}