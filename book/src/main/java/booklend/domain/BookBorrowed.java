package booklend.domain;

import booklend.domain.*;
import booklend.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class BookBorrowed extends AbstractEvent {

    private Long id;
    private Long bookId;
    private String status;
    private Long userId;
    private Date createDt;
}
