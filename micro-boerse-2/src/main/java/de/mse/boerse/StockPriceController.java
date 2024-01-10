package de.mse.boerse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kurs")
public class StockPriceController {

    @Autowired
    private StockPriceService stockPriceService;

    @GetMapping
    public ResponseEntity<List<StockData>> getStockPrices(@RequestParam String symbols) {
        return ResponseEntity.ok(stockPriceService.getStockPrices(symbols));
    }
    
}
