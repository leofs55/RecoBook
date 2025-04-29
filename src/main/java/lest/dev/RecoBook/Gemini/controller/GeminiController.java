package lest.dev.RecoBook.Gemini.controller;

import lest.dev.RecoBook.Gemini.service.GeminiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping("/recomandation")
    public Mono<String> generateRecomandation() {
        return geminiService.generateRecoBook();
    }


}
