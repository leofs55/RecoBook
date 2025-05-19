package lest.dev.RecoBook.service;

import lest.dev.RecoBook.controller.request.BookRequest;
import lest.dev.RecoBook.entity.Book;
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
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}${gemini.api.key}")
    private String url;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecoBook(List<Book> listBooks) {
        String books = listBooks.stream()
                .map(book -> String.format(
                        "Nome: %s, Genero: %s, Data de inicio da leitura: %s, Data de fim da leitura: %s, Avaliação do livro: %s",
                        book.getName(), book.getGenre(), book.getDateStart(), book.getDateEnd(), book.getEvaluation()))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseado nessa lista de leituras feitas por mim e considere todos os campos e livros para me recomendar um livro. " + books;
        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of(
                                                "text", prompt
                                        )
                                )
                        )
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
                        return text.get("text").toString();
                    }
                    return "Nenhuma livro foi recomendado!";
                });
    }

}
