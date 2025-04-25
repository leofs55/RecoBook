package lest.dev.RecoBook.service;

import lest.dev.RecoBook.model.BookModel;
import lest.dev.RecoBook.repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel createBook(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    public BookModel alterBook(Long id, BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookModel> listBooks () {
        return bookRepository.findAll();
    }

    public Optional<BookModel> listByIdBook(Long id) {
        return bookRepository.findById(id);
    }

}
