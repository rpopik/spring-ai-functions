package guru.springframework.springaifunctions.services;


import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OpenAIWeatherService {

    Answer getAnswer(Question question);
}