package lest.dev.RecoBook.Book.controller;

import lest.dev.RecoBook.Book.dto.BookDTO;
import lest.dev.RecoBook.Book.model.Book;
import lest.dev.RecoBook.Book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        BookDTO bookDTO = bookService.listByIdBook(id);
        if (bookDTO != null) {
            return ResponseEntity.ok(bookService.listByIdBook(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.ok(bookService.listBooks());
    }

    @GetMapping("/list/{user_id}")
    public ResponseEntity<List<BookDTO>> getBooksByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.listBooksByUserId(id));
    }

    @PostMapping("/register")
    public ResponseEntity<BookDTO> postBook( @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> alterBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO bookAtt = bookService.alterBook(id, bookDTO);
        if (bookAtt != null) {
            return ResponseEntity.ok(bookAtt);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean resultDeletion = bookService.deleteBook(id);
        if (resultDeletion) {
            return ResponseEntity.ok("Book deleted");
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }
}
