package lest.dev.RecoBook.dto;

import lombok.Builder;

@Builder
public record JWTUser(Long id, String email) {
}
