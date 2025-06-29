package guru.springframework.springaifunctions.config;

public class SystemConstants {

    // Http Header constants
    public static final String X_API_KEY = "X-api-key";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APP_JSON = "application/json";
    public static final String ACCEPT = "Accept";

    // Ninja API constants
    public static final String NINJA_BASE_URL = "https://api.api-ninjas.com/v1/weather";

    // OpenAI Weather Service constants
    public static final String WEATHER_SYSTEM_PROMPT = """
            You are a weather service. You receive weather information from a service
            which gives you the information based on the metrics system.
            When answering the weather in an imperial system country, you
            should convert the temperature to Fahrenheit and the wind speed to miles per hour, sunrise and sunset to human readable form.
            Do not include the conversion notes in your response.
            """;
    public static final String WEATHER_CALLBACK_DESCRIPTION = "Get the current weather for this location";
    public static final String WEATHER_CALLBACK_FN_NAME = "CurrentWeather";
    public static final String WEATHER_ENDPOINT = "/weather";

    public static final String STOCK_PRICE_ENDPOINT = "/stockprice";
    public static final String STOCK_PRICE_BASE_URL = "https://api.api-ninjas.com/v1/stockprice";
    public static final String STOCK_PRICE_SYSTEM_PROMPT = """
            You are a stock price service. You receive stock information from a service
            which gives you the information based on the ticker symbol.
            When answering the stock price, you should return the current price of the stock.
            """;

}