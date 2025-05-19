package lest.dev.RecoBook.service;

import lest.dev.RecoBook.entity.Book;
import lest.dev.RecoBook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book alterBook(Long id, Book book) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookRepository.save(book);
        }
        return null;
    }


    public boolean deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listBookByUserId(Long userId) {
        return bookRepository.findBookByUserId(userId);
    }

    public Optional<Book> listByIdBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional;
        }
        return Optional.empty();
    }

}
