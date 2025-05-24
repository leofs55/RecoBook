package lest.dev.RecoBook.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserRequest(@NotBlank(message = "Este campo é obrigatorio insira um nome!")
                          @Size(max = 255, message = "Insira um nome com até 255 caracteres(incluindo espaços em branco!)")
                          String firstName,
                          @NotBlank(message = "Este campo é obrigatorio insira um nome!")
                          @Size(max = 255, message = "Insira um nome com até 255 caracteres(incluindo espaços em branco!)")
                          String lastName,
                          @Past(message = "Insira a data de seu nascimento!!")
                          LocalDate birthDate,
                          @Email(message = "Insira um email valido!")
                          String email,
                          @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")
                          String password) {
}
