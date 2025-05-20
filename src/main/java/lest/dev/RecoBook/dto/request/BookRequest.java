package lest.dev.RecoBook.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookRequest(String name,
                          String genre,
                          LocalDate dateStart,
                          LocalDate dateEnd,
                          String evaluation,
                          Long user_id) {
}
