package com.reading.getirreading.book.domain.repository;

import com.reading.getirreading.book.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/*
    @Author Muhammet Feyzi Saglam 06.02.2022
    i use spring data jpa refernce url is -->https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
    and if you have got custom queries yours pllication we must override and re write any functions jpql queries and etc.

 */

@Repository
public interface BookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {
}
