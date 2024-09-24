package booklend.domain;

import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class BookApproved extends AbstractEvent {

    private Long id;
    private String status;
    private Date updateDt;
    private Long borrowId;
    private String borrowStatus;
}
