package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class BookRejected extends AbstractEvent {

    private Long id;
    private String status;
    private Date updateDt;
    private Long borrowId;
    private String borrowStatus;
}
