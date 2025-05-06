package lest.dev.RecoBook.Book.service;

import lest.dev.RecoBook.Book.dto.BookDTO;
import lest.dev.RecoBook.Book.mapper.BookMapper;
import lest.dev.RecoBook.Book.model.Book;
import lest.dev.RecoBook.Book.repository.BookRepository;
import lest.dev.RecoBook.User.model.User;
import lest.dev.RecoBook.User.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.map(bookDTO);
        return bookMapper.map(bookRepository.save(book));
    }

    public BookDTO alterBook(Long id, BookDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookDTO.setId(id);
            Book bookAtt = bookMapper.map(bookDTO);
            bookRepository.save(bookAtt);
            return bookMapper.map(bookAtt);
        }
        return null;
    }


    public boolean deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BookDTO> listBooks () {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::map)
                .collect(Collectors.toList());
    }

    public BookDTO listByIdBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return bookMapper.map(book);
        }
        return null;
    }

}
