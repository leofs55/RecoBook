package lest.dev.RecoBook.repository;

import lest.dev.RecoBook.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {
}
