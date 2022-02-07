package com.reading.getirreading.order.domain.repository;

import com.reading.getirreading.order.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Optional<List<Order>> findByCustomerId(Long customerId);

    // this is jpq query fundemantal base
    @Query("SELECT u FROM Order u WHERE u.id =1 ORDER BY 1 ASC")
    Optional<Order> getOrderById(Long id);

    // this is param type custom query
    @Query("from Order where createDateTime between :start and :end ")
    List<Order> findOrderBetweenCreatedDate (@Param("start")Date start ,@Param("end") Date end);

    @Query("from Order where customerId =customerId")
    Page<Order> getOrdersByCustomerId(@Param("customerId") Long customerId);

}