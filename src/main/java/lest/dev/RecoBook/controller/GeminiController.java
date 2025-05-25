package lest.dev.RecoBook.controller;

import lest.dev.RecoBook.config.TokenService;
import lest.dev.RecoBook.dto.JWTUser;
import lest.dev.RecoBook.dto.response.GeminiResponse;
import lest.dev.RecoBook.entity.Book;
import lest.dev.RecoBook.service.BookService;
import lest.dev.RecoBook.service.GeminiService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GeminiController {

    private final BookService bookService;
    private final GeminiService geminiService;
    private final TokenService tokenService;

    @GetMapping("/generate_book")
    public Mono<ResponseEntity<List<GeminiResponse>>> generateBook(@PathVariable Long id) {
        //Pega a JWT do User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<JWTUser> authenticated = tokenService.validateToken((String) authentication.getCredentials());

        if (authenticated.isPresent()) {
            JWTUser userLogged = (JWTUser) authentication.getPrincipal();
            List<Book> books = bookService.listBookByUserId(userLogged.id());
            return geminiService.generateRecoBook(books)
                    .map(recomendation -> ResponseEntity.ok(recomendation))
                    .defaultIfEmpty(ResponseEntity.noContent().build());
        }
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.emptyList()));
    }

}
