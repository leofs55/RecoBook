package lest.dev.RecoBook.controller;

import lest.dev.RecoBook.model.BookModel;
import lest.dev.RecoBook.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BookModel> getBook(@PathVariable Long id) {

    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getBooks() {

    }

    public ResponseEntity<BookModel> postBook() {

    }

    public ResponseEntity<BookModel> getBook() {

    }

    public ResponseEntity<BookModel> getBook() {

    }
}
