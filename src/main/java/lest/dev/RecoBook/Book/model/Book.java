package lest.dev.RecoBook.Book.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    @Lob
    private String evaluation;
    @JoinColumn(name = "user_id")
    private Long user_id;

}
