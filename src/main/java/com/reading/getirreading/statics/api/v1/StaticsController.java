package com.reading.getirreading.statics.api.v1;

import com.reading.getirreading.statics.domain.service.StaticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "/statics")
public class StaticsController {

    private final StaticsService staticsService ;

    public StaticsController(StaticsService staticsService){
        this.staticsService=staticsService;
    }
    @GetMapping("/bookCount")
    public int getBookCountMounthly(@RequestParam Date mounth,@RequestParam Long id){
        return staticsService.getBookCountMounthly(mounth,id);
    }
    @GetMapping("/orderCount")
    public int getOrderCountMounthly(@RequestParam Date mounth, @RequestParam Long id){
        return staticsService.getOrderCountMounthly(mounth,id);
    }

    @GetMapping("/purchasedAmount")
    public double  getPruchasedCountMounthly(@RequestParam Date mounth, @RequestParam Long bookId,@RequestParam Long orderId){
        return staticsService.getPurchasedCountMounthly(mounth,bookId,orderId);
    }

}
