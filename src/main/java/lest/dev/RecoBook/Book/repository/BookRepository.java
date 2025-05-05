package lest.dev.RecoBook.Book.repository;

import lest.dev.RecoBook.Book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Books b WHERE b.user.id = :id_user")
    List<Book> findBookByUserId(@Param("id_user") Long id);

}
