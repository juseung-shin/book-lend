package booklend.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RetrunCommand {

    private Long userId;
    private Long bookId;
}
