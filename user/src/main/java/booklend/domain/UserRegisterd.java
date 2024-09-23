package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class UserRegisterd extends AbstractEvent {

    private Long id;
    private String name;
    private String createDt;
    private String updateDt;

    public UserRegisterd(User aggregate) {
        super(aggregate);
    }

    public UserRegisterd() {
        super();
    }
}
//>>> DDD / Domain Event
