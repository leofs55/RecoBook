package lest.dev.RecoBook.repository;

import lest.dev.RecoBook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByUserId(Long id);

}
