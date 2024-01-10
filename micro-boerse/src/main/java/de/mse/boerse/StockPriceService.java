package de.mse.boerse;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class StockPriceService {

    @Value("${marketstack.api.url}")
    private String apiUrl;

    @Value("${marketstack.access.key}")
    private String accessKey;

    private final RestTemplate restTemplate;

    public StockPriceService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<StockData> getStockPrices(String symbols) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(10);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(apiUrl + "/eod")
                .queryParam("access_key", accessKey)
                .queryParam("symbols", symbols)
                .queryParam("date_from", startDate)
                .queryParam("date_to", endDate);

        ResponseEntity<MarketStackResponse> response = restTemplate.getForEntity(uriBuilder.toUriString(), MarketStackResponse.class);
        return response.getBody().getData();
    }
}

