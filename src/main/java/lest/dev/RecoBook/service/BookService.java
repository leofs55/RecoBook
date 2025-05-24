package lest.dev.RecoBook.service;

import lest.dev.RecoBook.entity.Book;
import lest.dev.RecoBook.entity.User;
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

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listBookByUserId(Long userId) {
        return bookRepository.findBookByUserId(userId);
    }

    //Atribuir o usuario que está mandando o update SOMENTE se ele for o propietario do book
    public Book alterBook(Long id, Book bookRequest, Long userId) {
        //Primeiro verificamos se o livro existe:
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            //Depois verificamos se ele realmente pertence ao usuario solicitante:
            Book book = bookOptional.get();
            User user = book.getUser();

            if (user.getId().equals(userId)) {
                //Para assim aplicarmos no banco
                return bookRepository.save(bookRequest);
            }
        }
        return null;
    }

    public Optional<Book> findByIdBook(Long id, Long userId) {
        //Primeiro verificamos se o livro existe:
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            //Depois verificamos se ele realmente pertence ao usuario solicitante:
            Book book = bookOptional.get();
            User user = book.getUser();

            if (user.getId().equals(userId)) {
                return bookOptional;
            }
        }
        return Optional.empty();
    }

    public boolean deleteBook(Long id, Long userId) {
        //Primeiro verificamos se o livro existe:
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            //Depois verificamos se ele realmente pertence ao usuario solicitante:
            Book book = bookOptional.get();
            User user = book.getUser();

            if (user.getId().equals(userId)) {
                bookRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

}
