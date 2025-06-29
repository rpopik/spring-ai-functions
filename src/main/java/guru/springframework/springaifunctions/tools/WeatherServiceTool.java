package guru.springframework.springaifunctions.tools;

import guru.springframework.springaifunctions.model.WeatherRequest;
import guru.springframework.springaifunctions.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


import java.util.function.Function;

import static guru.springframework.springaifunctions.config.SystemConstants.*;

public class WeatherServiceTool implements Function<WeatherRequest, WeatherResponse> {


    String apiKey;
    String baseUrl;

    public WeatherServiceTool(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set(X_API_KEY, apiKey);
                    httpHeaders.set(CONTENT_TYPE, APP_JSON);
                    httpHeaders.set(ACCEPT, APP_JSON);
                })
                .build();

        return restClient.get().uri(uriBuilder -> {
            uriBuilder.queryParam("lat", weatherRequest.lat())
                    .queryParam("lon", weatherRequest.lon());

            return uriBuilder.build();
        }).retrieve().body(WeatherResponse.class);
    }
}