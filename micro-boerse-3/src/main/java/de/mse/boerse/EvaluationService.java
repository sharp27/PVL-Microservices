package de.mse.boerse;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EvaluationService {

    private final RestTemplate restTemplate;

    // URL für App 1
    private final String app1ServiceUrl = "http://localhost:8081/kurs";

    // URL für App 2
    private final String app2ServiceUrl = "http://localhost:8082/kurs";


    public EvaluationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private LocalDate convertStringToDate(String dateString) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendOffset("+HHMM", "Z")
            .toFormatter();

        return ZonedDateTime.parse(dateString, formatter).toLocalDate();
    }

    public EvaluationData evaluateDataForService(String symbol, String service) {
        String serviceUrl = service.equals("app1") ? app1ServiceUrl : app2ServiceUrl;
        serviceUrl = UriComponentsBuilder.fromUriString(serviceUrl)
                .queryParam("symbols", symbol)
                .toUriString();

        ResponseEntity<List<StockData>> response = restTemplate.exchange(serviceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<StockData>>() {});
        List<StockData> stockDataList = response.getBody();

        return new EvaluationData(symbol, calculatePercentageChanges(stockDataList));
    }

    private List<DailyChange> calculatePercentageChanges(List<StockData> stockDataList) {
    	
        // Sortieren der Liste nach Datum
        stockDataList.sort(Comparator.comparing(stockData -> convertStringToDate(stockData.getDate())));

        List<DailyChange> changes = new ArrayList<>();
        for (int i = 1; i < stockDataList.size(); i++) {
            StockData current = stockDataList.get(i);
            StockData previous = stockDataList.get(i-1);
            double change = ((current.getClose() - previous.getClose()) / previous.getClose()) * 100;
            LocalDate date = convertStringToDate(current.getDate());
            changes.add(new DailyChange(date, change, current.getClose()));
        }
        
        // Umkehren der Liste, damit das aktuellste Datum zuerst erscheint
        Collections.reverse(changes);
        
        return changes;
    }
    
    
}




