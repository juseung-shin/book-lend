package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookReturned extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long bookId;
    private String status;

    public BookReturned(Borrowing aggregate) {
        super(aggregate);
    }

    public BookReturned() {
        super();
    }
}
//>>> DDD / Domain Event
