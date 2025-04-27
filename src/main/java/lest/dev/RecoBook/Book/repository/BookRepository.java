package lest.dev.RecoBook.Book.repository;

import lest.dev.RecoBook.Book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
