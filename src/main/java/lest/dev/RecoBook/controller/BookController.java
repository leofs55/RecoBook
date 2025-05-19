package lest.dev.RecoBook.controller;

import lest.dev.RecoBook.controller.request.BookRequest;
import lest.dev.RecoBook.controller.response.BookResponse;
import lest.dev.RecoBook.entity.Book;
import lest.dev.RecoBook.mapper.BookMapper;
import lest.dev.RecoBook.service.BookService;
import lest.dev.RecoBook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/details/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        Optional<Book> bookOp = bookService.listByIdBook(id);
        return bookOp
                .map(book -> ResponseEntity.ok(BookMapper.map(book)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookResponse>> getBooks() {
        List<BookResponse> list = bookService.listBooks().stream()
                .map(BookMapper::map)
                .toList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/list/{user_id}")
    public ResponseEntity<List<BookResponse>> getBooksByUserId(@PathVariable Long user_id) {
        List<BookResponse> list = bookService.listBookByUserId(user_id).stream()
                .map(BookMapper::map)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/register")
    public ResponseEntity<BookResponse> postBook(@RequestBody BookRequest bookRequest) {
        Book book = BookMapper.map(bookRequest);
        book.setUser(userService.detailUser(book.getUser().getId()).orElse(null));
        return ResponseEntity.ok(BookMapper.map(bookService.createBook(book)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<BookResponse>> alterBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        Book book = BookMapper.map(bookRequest);
        book.setUser(userService.detailUser(book.getUser().getId()).orElse(null));
        Book bookAtt = bookService.alterBook(id, book);
        if (bookAtt != null) {
            return ResponseEntity.ok(Optional.of(BookMapper.map(bookAtt)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean resultDeletion = bookService.deleteBook(id);
        if (resultDeletion) {
            return ResponseEntity.ok("Book deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }
}
