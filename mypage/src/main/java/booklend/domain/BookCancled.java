package booklend.domain;

import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class BookCancled extends AbstractEvent {

    private Long id;
    private Date updateDt;
    private Long borrowid;
    private String status;
    private String borrowStatus;
}
