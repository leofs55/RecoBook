package lest.dev.RecoBook.Gemini.controller;

import lest.dev.RecoBook.Book.dto.BookDTO;
import lest.dev.RecoBook.Gemini.service.GeminiService;
import lest.dev.RecoBook.User.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GeminiController {

    private final UserService userService;
    private final GeminiService geminiService;

    @GetMapping("/generate_book/{id}")
    public Mono<ResponseEntity<String>> generateBook(@PathVariable Long id){
        List<BookDTO> books = userService.userBooks(id);
        return geminiService.generateRecoBook(books)
                .map(recomendation -> ResponseEntity.ok(recomendation))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
