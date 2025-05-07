package lest.dev.RecoBook.Gemini.controller;

import lest.dev.RecoBook.Book.dto.BookDTO;
import lest.dev.RecoBook.Book.model.Book;
import lest.dev.RecoBook.Gemini.service.GeminiService;
import lest.dev.RecoBook.User.dto.UserDTO;
import lest.dev.RecoBook.User.model.User;
import lest.dev.RecoBook.User.service.UserService;
import lombok.AllArgsConstructor;
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

    private final GeminiService geminiService;
    private final UserService userService;

    @GetMapping("/recomandation/{id}")
    public Mono<String> generateRecomandation(@PathVariable Long id) {
        UserDTO userDTO = userService.detailUser(id);
        List<Book> bookList = userDTO.getBooks();
        return geminiService.generateRecoBook(bookList.stream()
                .map(book -> ));
    }


}
