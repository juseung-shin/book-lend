package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class BookCancled extends AbstractEvent {

    private Long id;
    private Date updateDt;
    private Long borrowid;
    private String status;
    private String borrowStatus;
}
