package guru.springframework.springaifunctions.services;

import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;

public interface OpenAIStockService {
    Answer getStock(Question question);
}