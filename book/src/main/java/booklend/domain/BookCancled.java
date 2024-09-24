package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookCancled extends AbstractEvent {

    private Long id;
    private Date updateDt;
    private Long borrowid;
    private String status;
    private String borrowStatus;

    public BookCancled(Book aggregate) {
        super(aggregate);
    }

    public BookCancled() {
        super();
    }
}
//>>> DDD / Domain Event
