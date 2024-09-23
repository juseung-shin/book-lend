package booklend.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateCommand {

    private Long id;
    private String title;
    private String status;
    private Date createDt;
}
