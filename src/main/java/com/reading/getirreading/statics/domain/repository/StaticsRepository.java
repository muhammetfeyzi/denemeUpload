package com.reading.getirreading.statics.domain.repository;

import com.reading.getirreading.statics.domain.model.Statics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface StaticsRepository extends JpaRepository<Statics, Long> ,JpaSpecificationExecutor<Statics> {
    @Query("from Statics where bookId =id and ") // left join yazilacak statics ile book icin
    int getBookCountMounthly(@Param("mounth")Date mounth,@Param("id") Long id);

    @Query("from Statics where bookId =id and ") // left join yazilacak statics ile order icin
    int getOrderCountMounthly(@Param("mounth")Date mounth,@Param("id") Long id);

    @Query//--> we will calculate mounthly price purchased
    double getPurchasedCountMounthly(@Param("mounth") Date mounth,@Param("orderId") Long orderId, @Param("bookId") Long bookId);
}
