package guru.springframework.springaifunctions.model;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Weather API Request")
public record WeatherRequest(
        @JsonProperty(required = true, value = "lat")
        @JsonPropertyDescription("The latitude of the location. Use decimal format, e.g., 37.7749")
        BigDecimal lat,

        @JsonProperty(required = true, value = "lon")
        @JsonPropertyDescription("The longitude of the location. Use decimal format, e.g., -122.4194")
        BigDecimal lon
) {
}