package lest.dev.RecoBook.Book.controller;

import lest.dev.RecoBook.Book.model.Book;
import lest.dev.RecoBook.Book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.listByIdBook(id).orElse(null));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.listBooks());
    }

    @PostMapping("/create")
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PutMapping("/alter/{id}")
    public ResponseEntity<?> alterBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.alterBook(id, book));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Deletado");
    }
}
