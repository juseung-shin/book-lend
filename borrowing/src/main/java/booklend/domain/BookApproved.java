package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class BookApproved extends AbstractEvent {

    private Long id;
    private Date updateDt;
    private Long borrowId;
    private String borrowStatus;
}
