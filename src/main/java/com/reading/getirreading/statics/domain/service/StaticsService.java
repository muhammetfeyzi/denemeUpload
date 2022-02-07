package com.reading.getirreading.statics.domain.service;

import com.reading.getirreading.statics.domain.repository.StaticsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StaticsService {

    private final StaticsRepository staticsRepository;

    public StaticsService(StaticsRepository staticsRepository){
        this.staticsRepository=staticsRepository;
    }

    public int getBookCountMounthly(Date mounth,Long id){
        return staticsRepository.getBookCountMounthly(mounth,id);
    }
    public int getOrderCountMounthly(Date mounth,Long id){
        return staticsRepository.getBookCountMounthly(mounth,id);
    }
    public double  getPurchasedCountMounthly(Date mounth,Long bookId,Long orderId){
        return staticsRepository.getPurchasedCountMounthly(mounth,bookId,orderId);
    }


}
