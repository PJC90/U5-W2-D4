package pierpaolo.u5w2d2.payloads;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime time) {
}
