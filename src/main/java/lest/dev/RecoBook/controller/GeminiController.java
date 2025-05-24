package lest.dev.RecoBook.controller;

import lest.dev.RecoBook.dto.response.GeminiResponse;
import lest.dev.RecoBook.entity.Book;
import lest.dev.RecoBook.service.BookService;
import lest.dev.RecoBook.service.GeminiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final BookService bookService;
    private final GeminiService geminiService;

    @GetMapping("/generate_book/{id}")
    public Mono<ResponseEntity<List<GeminiResponse>>> generateBook(@PathVariable Long id) {
        //Pega a JWT do User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<Book> books = bookService.listBookByUserId(id);
        return geminiService.generateRecoBook(books)
                .map(recomendation -> ResponseEntity.ok(recomendation))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
