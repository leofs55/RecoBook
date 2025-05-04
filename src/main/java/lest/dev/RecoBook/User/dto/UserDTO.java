package lest.dev.RecoBook.User.dto;

import lest.dev.RecoBook.Book.model.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String first_name;

    private String last_name;

    private LocalDate birth_date;

    private List<Book> books;

    private String email;

}
