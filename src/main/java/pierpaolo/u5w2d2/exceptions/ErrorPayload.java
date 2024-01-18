package pierpaolo.u5w2d2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class ErrorPayload {
    private String message;
    private LocalDateTime time;
}
