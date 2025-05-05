package lest.dev.RecoBook.Book.model;

import jakarta.persistence.*;
import lest.dev.RecoBook.User.model.User;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String genre;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private String evaluation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
