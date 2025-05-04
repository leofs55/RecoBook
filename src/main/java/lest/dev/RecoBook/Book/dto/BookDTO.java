package lest.dev.RecoBook.Book.dto;

import lest.dev.RecoBook.User.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;

    private String name;

    private String genre;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private String evaluation;

    private User user;

}
