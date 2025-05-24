package lest.dev.RecoBook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lest.dev.RecoBook.dto.response.GeminiResponse;
import lest.dev.RecoBook.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final WebClient webClient;
    private final ObjectMapper mapper;

    @Value("${gemini.api.url}${gemini.api.key}")
    private String url;

    public Mono<List<GeminiResponse>> generateRecoBook(List<Book> listBooks) {
        String books = listBooks.stream()
                .map(book -> String.format(
                        "Li um livro chamado %s do genero %s que comecei a ler dia %s e terminei dia %s minhas considerações sobre livro são: %s.",
                        book.getName(), book.getGenre(), book.getDateStart(), book.getDateEnd(), book.getEvaluation()))
                .collect(Collectors.joining("\n"));

        String prompt = books + "Baseado nessa lista de leituras feitas por mim me 5 recomendações de livros e considere todas as informações.";
        Map<String, Object> responseSchema = Map.of(
                "type", "ARRAY",
                "items", Map.of(
                        "type", "OBJECT",
                        "properties", Map.of(
                                "nameBook", Map.of("type", "STRING"),
                                "genreBook", Map.of("type", "STRING"),
                                "resumeBook", Map.of("type", "STRING")
                        )
                )
        );

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                ),
                "generationConfig", Map.of(
                        "responseMimeType", "application/json",
                        "responseSchema", responseSchema
                )
        );
        return webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Map<String, List<Map<String, Object>>>>>) response.get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        Map<String, Object> text = (Map<String, Object>) candidates.get(0).get("content").get("parts").get(0);
                        String resposta = text.get("text").toString();
                        List<GeminiResponse> responses = null;
                        try {
                            responses = mapper.readValue(
                                    resposta,
                                    mapper.getTypeFactory().constructCollectionType(List.class, GeminiResponse.class)
                            );
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        return responses;
                    }
                    return List.of(GeminiResponse.builder().build());
                });
    }
}
