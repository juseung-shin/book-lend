package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookBorrowed extends AbstractEvent {

    private Long id;
    private String bookId;
    private String status;
    private String userId;
    private Date createDt;

    public BookBorrowed(Borrowing aggregate) {
        super(aggregate);
    }

    public BookBorrowed() {
        super();
    }
}
//>>> DDD / Domain Event
