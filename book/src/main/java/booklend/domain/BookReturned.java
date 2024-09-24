package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class BookReturned extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long bookId;
    private String status;
}
