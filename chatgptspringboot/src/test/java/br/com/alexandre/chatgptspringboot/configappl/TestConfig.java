package br.com.alexandre.chatgptspringboot.configappl;

import com.theokanning.openai.service.OpenAiService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public OpenAiService openAiService() {
        return Mockito.mock(OpenAiService.class);
    }
}
