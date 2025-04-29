package lest.dev.RecoBook.config;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lest.dev.RecoBook.Gemini.service.GeminiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
