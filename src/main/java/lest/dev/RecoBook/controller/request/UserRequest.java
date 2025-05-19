package lest.dev.RecoBook.controller.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserRequest(String firstName,
                          String lastName,
                          LocalDate birthDate,
                          String email,
                          String password) {
}
