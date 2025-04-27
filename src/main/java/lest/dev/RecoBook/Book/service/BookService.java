package lest.dev.RecoBook.Book.service;

import lest.dev.RecoBook.Book.model.Book;
import lest.dev.RecoBook.Book.repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book bookModel) {
        return bookRepository.save(bookModel);
    }

    public Book alterBook(Long id, Book bookModel) {
        return bookRepository.save(bookModel);
    }

    public void deleteBook(Long id) { bookRepository.deleteById(id); }

    public List<Book> listBooks () {
        return bookRepository.findAll();
    }

    public Optional<Book> listByIdBook(Long id) {
        return bookRepository.findById(id);
    }


}
