package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookApproved extends AbstractEvent {

    private Long id;
    private String status;
    private Date updateDt;
    private Long borrowId;
    private String borrowStatus;

    public BookApproved(Book aggregate) {
        super(aggregate);
    }

    public BookApproved() {
        super();
    }
}
//>>> DDD / Domain Event
