package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockRequest(
        @JsonProperty(required = true, value = "ticker")
        @JsonPropertyDescription("Stock or index ticker symbol (e.g., AAPL or ^DJI)")
        String ticker) {
}