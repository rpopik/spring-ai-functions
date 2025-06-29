package guru.springframework.springaifunctions.controllers;


import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;
import guru.springframework.springaifunctions.services.OpenAIStockService;
import guru.springframework.springaifunctions.services.OpenAIWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static guru.springframework.springaifunctions.config.SystemConstants.STOCK_PRICE_ENDPOINT;
import static guru.springframework.springaifunctions.config.SystemConstants.WEATHER_ENDPOINT;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final OpenAIWeatherService openAIWeatherService;
    private final OpenAIStockService openAIStockService;

    @PostMapping(WEATHER_ENDPOINT)
    public Answer askQuestion(@RequestBody Question question) {
        return openAIWeatherService.getAnswer(question);
    }

    @PostMapping(STOCK_PRICE_ENDPOINT)
    public Answer askStockPrice(@RequestBody Question question) {
        return openAIStockService.getStock(question);
    }

}