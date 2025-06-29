package guru.springframework.springaifunctions.tools;

import guru.springframework.springaifunctions.model.StockRequest;
import guru.springframework.springaifunctions.model.StockResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.web.client.RestClient;

import static guru.springframework.springaifunctions.config.SystemConstants.*;

public class StockServiceTool {

    private final String apiKey;

    private final String apiUrl;

    public StockServiceTool(String ninaApiKey, String ninaApiUrl) {
        this.apiKey = ninaApiKey;
        this.apiUrl = ninaApiUrl;
    }

    @Tool(description = "call the external stock API")
    public StockResponse getStock(StockRequest stockRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(apiUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set(X_API_KEY, apiKey);
                    httpHeaders.set(CONTENT_TYPE, APP_JSON);
                    httpHeaders.set(ACCEPT, APP_JSON);
                })
                .build();

        return restClient.get().uri(uriBuilder -> {
            uriBuilder.queryParam("ticker", stockRequest.ticker());
            return uriBuilder.build();
        }).retrieve().body(StockResponse.class);
    }
}