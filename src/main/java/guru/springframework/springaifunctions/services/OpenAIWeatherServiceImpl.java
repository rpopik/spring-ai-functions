package guru.springframework.springaifunctions.services;


import guru.springframework.springaifunctions.model.WeatherRequest;
import guru.springframework.springaifunctions.tools.WeatherServiceTool;
import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;
import guru.springframework.springaifunctions.model.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static guru.springframework.springaifunctions.config.SystemConstants.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
public class OpenAIWeatherServiceImpl implements OpenAIWeatherService {

    @Value("${sfg.ninja.api-key}")
    private String apiNinjasKey;


    private String apiNinjasBaseUrl = NINJA_BASE_URL;

    final OpenAiChatModel openAiChatModel;


    @Override
    public Answer getAnswer(Question question) {

        Message userMessage = new PromptTemplate(question.question()).createMessage();

        Message systemMessage = new SystemPromptTemplate(WEATHER_SYSTEM_PROMPT)
                .createMessage();

        String response  = ChatClient.create(openAiChatModel)
                .prompt(new Prompt(List.of(systemMessage, userMessage), getPromptOptions()))
                .toolCallbacks(FunctionToolCallback.builder(WEATHER_CALLBACK_FN_NAME, new WeatherServiceTool(apiNinjasKey, apiNinjasBaseUrl))
                        .description(WEATHER_CALLBACK_DESCRIPTION)
                        .inputType(WeatherRequest.class)
                        .inputSchema(ModelOptionsUtils.getJsonSchema(WeatherRequest.class, false))
                        .build())
                .call()
                .content();

        return new Answer(response);
    }

    private static OpenAiChatOptions getPromptOptions() {
        return OpenAiChatOptions.builder()
                .toolCallbacks()
                .build();
    }

//    private static FunctionToolCallback<WeatherRequest, WeatherResponse> getFunctionCallback() {
//        var callback  = FunctionToolCallback.builder(WEATHER_CALLBACK_FN_NAME, new WeatherServiceTool())
//                .description(WEATHER_CALLBACK_DESCRIPTION)
//                .inputType(WeatherRequest.class)
//                .inputSchema(ModelOptionsUtils.getJsonSchema(WeatherRequest.class, false))



//                .responseConverter(response ->{
//                    String schema = ModelOptionsUtils.getJsonSchema(WeatherResponse.class, false);
//                    String json = ModelOptionsUtils.toJsonString(response);
//                    return schema + "\n" + json;
//                })
//            .build();
//        return callback;
//    }
}