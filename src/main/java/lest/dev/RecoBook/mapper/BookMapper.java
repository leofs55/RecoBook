package lest.dev.RecoBook.mapper;

import lest.dev.RecoBook.controller.request.BookRequest;
import lest.dev.RecoBook.controller.request.UserRequest;
import lest.dev.RecoBook.controller.response.BookResponse;
import lest.dev.RecoBook.entity.Book;
import lest.dev.RecoBook.entity.User;
import lest.dev.RecoBook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class BookMapper {


    public static Book map(BookRequest bookRequest) {
        return Book.builder()
                .name(bookRequest.name())
                .genre(bookRequest.genre())
                .dateStart(bookRequest.dateStart())
                .dateEnd(bookRequest.dateEnd())
                .evaluation(bookRequest.evaluation())
                .user(User.builder()
                        .id(bookRequest.user_id())
                        .build())
                .build();
    }

    public static BookResponse map(Book book) {
        return BookResponse.builder()
                .name(book.getName())
                .genre(book.getGenre())
                .dateStart(book.getDateStart())
                .dateEnd(book.getDateEnd())
                .evaluation(book.getEvaluation())
                .user(UserMapper.map(book.getUser()))
                .build();
    }
}
