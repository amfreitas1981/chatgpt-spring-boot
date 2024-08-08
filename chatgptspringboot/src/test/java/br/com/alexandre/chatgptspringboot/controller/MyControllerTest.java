package br.com.alexandre.chatgptspringboot.controller;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MyControllerTest {

    @InjectMocks
    private MyController myController;

    @Mock
    private OpenAiService openAiService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        // Definindo os valores das propriedades @Value
        ReflectionTestUtils.setField(myController, "model", "fake-model");
        ReflectionTestUtils.setField(myController, "apiKey", "fake-api-key");
    }

    @Test
    public void testChat(){
        // Criação dos mocks necessários
        ChatMessage chatMessage = new ChatMessage("user", "Test prompt");
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(chatMessage);

        ChatCompletionChoice chatCompletionChoice = new ChatCompletionChoice();
        ChatMessage completionMessage = new ChatMessage("assistant", "Test response");
        chatCompletionChoice.setMessage(completionMessage);

        List<ChatCompletionChoice> choices = new ArrayList<>();
        choices.add(chatCompletionChoice);

        ChatCompletionResult chatCompletionResult = new ChatCompletionResult();
        chatCompletionResult.setChoices(choices);

        // Configurando o comportamento do mock do OpenAiService
        when(openAiService.createChatCompletion(any(ChatCompletionRequest.class))).thenReturn(chatCompletionResult);

        // Chamada do método a ser testado
        String response = myController.chat("Test prompt");

        // Verificação do resultado
        assertEquals("response: Test response" + System.lineSeparator(), response);
    }
}