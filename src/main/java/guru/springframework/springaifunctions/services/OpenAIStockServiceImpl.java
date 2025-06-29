package guru.springframework.springaifunctions.services;

import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;
import guru.springframework.springaifunctions.tools.StockServiceTool;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static guru.springframework.springaifunctions.config.SystemConstants.STOCK_PRICE_BASE_URL;
import static guru.springframework.springaifunctions.config.SystemConstants.STOCK_PRICE_SYSTEM_PROMPT;

@Service
@RequiredArgsConstructor
public class OpenAIStockServiceImpl implements OpenAIStockService {

    private final OpenAiChatModel openAiChatModel;

    @Value("${sfg.ninja.api-key}")
    private String ninaApiKey;

//    @Value(("${sfg.ninja.base-url}"))
//    private String ninaApiUrl;

    @Override
    public Answer getStock(Question question) {

        Message userMessage = new PromptTemplate(question.question()).createMessage();
        Message systemMessage = new SystemPromptTemplate(STOCK_PRICE_SYSTEM_PROMPT).createMessage();
        String response = ChatClient.create(openAiChatModel)
                .prompt(new Prompt(List.of(systemMessage, userMessage)))
                .tools(new StockServiceTool(ninaApiKey, STOCK_PRICE_BASE_URL))
                .call()
                .content();
        return new Answer(response);
    }
}