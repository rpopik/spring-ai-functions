package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

public record StockResponse(
        @JsonPropertyDescription("The stock symbol") String ticker,
        String name,
        BigDecimal price,
        String exchange,
        Integer updated,
        String currency
) {}