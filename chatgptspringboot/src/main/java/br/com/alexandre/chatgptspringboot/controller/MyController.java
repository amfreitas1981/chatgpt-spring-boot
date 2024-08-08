package br.com.alexandre.chatgptspringboot.controller;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    @Value("${openai.model}")
    private String model;

    private final OpenAiService openAiService;
    private final String apiKey;

    @Autowired
    public MyController(OpenAiService openAiService, @Value("${openai.api.key}") String apiKey) {
        this.openAiService = openAiService;
        this.apiKey = apiKey;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt){
        List<ChatMessage> messages = new ArrayList<ChatMessage>();
        ChatMessage message = new ChatMessage("user", prompt);
        messages.add(message);

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(messages)
                .model(model)
                .maxTokens(100)
                .temperature(0.6)
                .n(1)
                .build();

        List<ChatCompletionChoice> choices = openAiService.createChatCompletion(completionRequest).getChoices();

        StringBuilder returnString = new StringBuilder();
        for (ChatCompletionChoice choice:choices){
            returnString.append("response: ").append(choice.getMessage().getContent()).append(System.lineSeparator());
        }

        return returnString.toString();
    }
}
