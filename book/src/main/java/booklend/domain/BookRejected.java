package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookRejected extends AbstractEvent {

    private Long id;
    private String status;
    private Date updateDt;
    private Long borrowId;
    private String borrowStatus;

    public BookRejected(Book aggregate) {
        super(aggregate);
    }

    public BookRejected() {
        super();
    }
}
//>>> DDD / Domain Event
