package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

public record WeatherResponse(
    @JsonPropertyDescription("Wind speed in meters per second") BigDecimal windSpeed,
    @JsonPropertyDescription("Wind direction in degrees.") Integer windDegrees,
    @JsonPropertyDescription("Wind direction in degrees.") Integer temp,
    @JsonPropertyDescription("Current humidity percentage.") Integer humidity,
    @JsonPropertyDescription("Epoch time of sunset GMT") Integer sunset,
    @JsonPropertyDescription("Minimum temperature in Celsius.") Integer minTemp,
    @JsonPropertyDescription("Cloud Coverage Percentage") Integer cloudPct,
    @JsonPropertyDescription("What the temperature feels like in Celsius.") Integer feelsLike,
    @JsonPropertyDescription("Epoch time of sunset GMT") Integer sunrise,
    @JsonPropertyDescription("Maximum temperature in Celsius.") Integer maxTemp
){}