package com.reading.getirreading.util.OrderFunction;

import com.reading.getirreading.book.domain.model.Book;
import com.reading.getirreading.order.domain.model.OrderBook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import java.util.concurrent.Callable;

public class UpdateStock implements Callable<Void> {

    EntityManagerFactory emf;
    EntityManager em;

    Integer stock;
    EntityTransaction trx;
    Book book;
    OrderBook orderBook;


    public UpdateStock(EntityManagerFactory emf, EntityManager em,EntityTransaction trx, Integer stock) {
        this.emf = emf;
        this.em = em;
        this.stock=stock;
        this.trx=trx;
    }

    public void UpdateStockCall(OrderBook orderBook){
        this.orderBook = orderBook;
        this.call();
    }

    @Override
    public Void call() { // Runnable# function like that run

        try {

            trx.begin(); // Transaction begining
            book=em.find(Book.class, orderBook.getBookId()); // fetch bookId in book order

            Thread.sleep(500); // 500 ms suspend thread

            em.lock(book, LockModeType.OPTIMISTIC_FORCE_INCREMENT); // First come first transaction  maybe we will change LockModeType.OPTIMISTIC_FORCE_INCREMENT
             //sistemde diğer kaynakların kitlenmemesi için kullandım ama kitlenmenin önemli olmayıp kaynaga erişen thread işi yapması istenirse
            // PESIMISTIC yapı kullanılabilir.
            book.setStock(book.getStock()-stock); // stock change
            trx.commit(); // end of transaction and commit
        }
        catch (Throwable e) {
            trx.rollback(); // failure process
            e.printStackTrace();
        }
        return null;

    }
}