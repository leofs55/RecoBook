package lest.dev.RecoBook.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserLoginRequest(@Email(message = "Insira um email valido!")
                               String email,
                               @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")
                               String password) {
}
