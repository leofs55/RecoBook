package lest.dev.RecoBook.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookResponse(String name,
                           String genre,
                           LocalDate dateStart,
                           LocalDate dateEnd,
                           String evaluation,
                           UserResponse user) {
}
