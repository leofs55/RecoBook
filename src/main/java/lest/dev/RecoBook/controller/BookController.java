package lest.dev.RecoBook.controller;

import jakarta.validation.Valid;
import lest.dev.RecoBook.dto.JWTUser;
import lest.dev.RecoBook.dto.request.BookRequest;
import lest.dev.RecoBook.dto.response.BookResponse;
import lest.dev.RecoBook.entity.Book;
import lest.dev.RecoBook.mapper.BookMapper;
import lest.dev.RecoBook.service.BookService;
import lest.dev.RecoBook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BookResponse> postBook(@RequestBody @Valid BookRequest bookRequest) {
        //Pega a JWT do User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUser userLogged = (JWTUser) authentication.getPrincipal();

        Book book = BookMapper.map(bookRequest);
        book.setUser(userService.detailUser(userLogged.id()).orElse(null));
        return ResponseEntity.ok(BookMapper.map(bookService.createBook(book)));
    }

    //Criar cargo para, somente admins poderem acessar essa url
//    @GetMapping("/list")
//    public ResponseEntity<List<BookResponse>> getBooks() {
//        List<Book> list = bookService.listBooks();
//        return ResponseEntity.ok(list.stream()
//                .map(BookMapper::map)
//                .toList());
//    }

    @GetMapping("/details/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        //Pega a JWT do User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUser userLogged = (JWTUser) authentication.getPrincipal();

        Optional<Book> bookOp = bookService.findByIdBook(id, userLogged.id());
        return bookOp
                .map(book -> ResponseEntity.ok(BookMapper.map(book)))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/my-books")
    public ResponseEntity<List<BookResponse>> getBooksByUserId() {
        //Pega a JWT do User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUser userLogged = (JWTUser) authentication.getPrincipal();

        List<Book> list = bookService.listBookByUserId(userLogged.id());
        return ResponseEntity.ok(list.stream()
                .map(BookMapper::map)
                .toList());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<BookResponse>> alterBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest) {
        //Pega a JWT do User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUser userLogged = (JWTUser) authentication.getPrincipal();
        Book book = BookMapper.map(bookRequest);

        Book bookAtt = bookService.alterBook(id, book, userLogged.id());

        if (bookAtt != null) {
            return ResponseEntity.ok(Optional.of(BookMapper.map(bookAtt)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        //Pega a JWT do User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUser userLogged = (JWTUser) authentication.getPrincipal();

        boolean resultDeletion = bookService.deleteBook(id, userLogged.id());
        if (resultDeletion) {
            return ResponseEntity.ok("Book deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
    }
}
