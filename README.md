# getir-read-is-good
 ReadingIsGood
Getir interview Assesment
-----*****-----*****-----*****-----\n



ReadingIsGood is an online books retail firm which operates only on the Internet. Main  target of ReadingIsGood is to deliver books from its one centralized warehouse to their  customers within the same day. That is why stock consistency is the first priority for their  vision operations.

i designed my porject api layer sturctured not used tradational layer structure every model like that customer,book,order has got owner structure in our project.

---******----Spring Security Section ---*********--
WebSecurtiy Config Class Description
     This is base Spring Security Functions And this is extends Fundamental Functions that WebSecurityConfigurerAdapter
     If you learn more about spring security fundamental you can lokk up -->https://www.baeldung.com/java-config-spring-security
This is whiteList Our System endpoint
    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/configuration/**",
            "/webjars/**",
            "/h2-console/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**"
    };
Encoder client Password Algorithm
i use bcryptPAsswordEncoder if you think your algorithm is not secure we will change this algortihm diffrent override secure functions

---***-----Exception---****---
Every program naturally have got a lot of problem in run time or execution time so that i built exception package for managed system throws exception
i.Error Enum
    I created this enum so that when my program throws an error, it can be meaningfully managed from a single center.
    and i choose http1.0 status code managed our errors code and if you  learn more information you can look -->
    https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
ii.GlobalExceptionHandler
    i use Global Exception handler in spring boot project and using @ExceptionHandler anatation.
    you can visited https://www.studytonight.com/spring-boot/spring-boot-global-exception-handling for more information Global Exception Handler in Spring Boot
---***-----OPtimistic Locking Spring Datat Jpa ---****---
Will update stock records. 
(Hint: what if it happens if 2 or more users tries to buy one last book  
at the same time) so that i solved this issue optimistic locking jpa
data jpa has got two difrent loking system pesimistic and optimistic, optimistic locking system change your book id automatically if you managed your book`s id in your program



Used Technologies

-Java 11

-Spring-Boot

-H2 Database Engine

-Maven

-Docker

Swagger
Unit test
reading-is-good-api

http://localhost:9090/swagger-ui.html#/

Authentication

curl --location --request POST 'http://localhost:9090/authenticate' 
--header 'Content-Type: application/json' 
--data-raw '{ "username":"getir", "password":"getirPass" }'

Postman Collection Directory

getir-reading\postman-collection

