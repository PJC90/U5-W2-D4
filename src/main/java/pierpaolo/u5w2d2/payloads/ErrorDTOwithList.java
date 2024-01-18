package pierpaolo.u5w2d2.payloads;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record ErrorDTOwithList(
        String message,
        LocalDateTime timestamp,
        List<String> errorsList
) {
}
