package lest.dev.RecoBook.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookRequest(@NotBlank(message = "Este campo é obrigatorio insira um nome!")
                          @Size(max = 255, message = "Insira um nome com até 255 caracteres(incluindo espaços em branco!)")
                          String name,
                          @NotBlank(message = "Este campo é obrigatorio insira um genero!")
                          @Size(max = 255, message = "Insira um genero com até 255 caracteres(incluindo espaços em branco!)")
                          String genre,
                          @Past(message = "Insira um valor anterior a data atual!")
                          LocalDate dateStart,
                          @PastOrPresent
                          LocalDate dateEnd,
                          @NotEmpty(message = "Este campo é obrigatorio digite a sua avalição do livro")
                          @NotBlank(message = "Este campo é obrigatorio digite a sua avalição do livro")
                          String evaluation,
                          @Positive(message = "Id de usuário invalido!")
                          Long user_id) {
}
