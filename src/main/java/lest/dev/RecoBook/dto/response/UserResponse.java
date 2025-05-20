package lest.dev.RecoBook.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(String firstName,
                           String lastName,
                           LocalDate birthDate,
                           String email) {
}
