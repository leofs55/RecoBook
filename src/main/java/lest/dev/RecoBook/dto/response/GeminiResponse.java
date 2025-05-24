package lest.dev.RecoBook.dto.response;

import lombok.Builder;

@Builder
public record GeminiResponse(String genreBook,
                             String nameBook,
                             String resumeBook) {
}
