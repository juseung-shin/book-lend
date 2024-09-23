package booklend.domain;

import booklend.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class BookBorrowed extends AbstractEvent {

    private Long id;
    private String bookId;
    private String status;
    private String userId;
    private Date createDt;
}
