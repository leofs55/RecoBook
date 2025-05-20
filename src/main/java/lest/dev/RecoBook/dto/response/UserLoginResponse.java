package lest.dev.RecoBook.dto.response;

import lombok.Builder;

@Builder
public record UserLoginResponse(String token) {
}
