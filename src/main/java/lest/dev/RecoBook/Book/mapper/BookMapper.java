package lest.dev.RecoBook.Book.mapper;

import lest.dev.RecoBook.Book.dto.BookDTO;
import lest.dev.RecoBook.Book.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book map(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        book.setGenre(bookDTO.getGenre());
        book.setDateStart(bookDTO.getDateStart());
        book.setDateEnd(bookDTO.getDateEnd());
        book.setEvaluation(bookDTO.getEvaluation());
        book.setUser(bookDTO.getUser());
        return book;
    }

    public BookDTO map(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setDateStart(book.getDateStart());
        bookDTO.setDateEnd(book.getDateEnd());
        bookDTO.setEvaluation(book.getEvaluation());
        bookDTO.setUser(book.getUser());
        return bookDTO;
    }

}
