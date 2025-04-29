package lest.dev.RecoBook.Book.service;

import lest.dev.RecoBook.Book.model.Book;
import lest.dev.RecoBook.Book.repository.BookRepository;
import lest.dev.RecoBook.User.model.User;
import lest.dev.RecoBook.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {

        this.bookRepository = bookRepository;
        this.userRepository = userRepository;

    }

    public Book createBook(Book bookModel) {
        return bookRepository.save(bookModel);

    }

    public Book alterBook(Long id, Book bookModel) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookModel.setId(id);
            return bookRepository.save(bookModel);
        }
        return null;
    }

    public void deleteBook(Long id) {

        bookRepository.deleteById(id);

    }

    public List<Book> listBooks () {

        return bookRepository.findAll();

    }

    public Optional<Book> listByIdBook(Long id) {

        return bookRepository.findById(id);

    }


}
