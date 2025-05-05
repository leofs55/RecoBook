package lest.dev.RecoBook.Gemini.controller;

import lest.dev.RecoBook.Book.dto.BookDTO;
import lest.dev.RecoBook.Gemini.service.GeminiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping("/recomandation")
    public Mono<String> generateRecomandation(List<BookDTO> listBooks) {
        return geminiService.generateRecoBook(listBooks);
    }


}
