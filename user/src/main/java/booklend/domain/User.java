package booklend.domain;

import booklend.UserApplication;
import booklend.domain.UserRegisterd;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String createDt;

    private String updateDt;

    @PostPersist
    public void onPostPersist() {
        UserRegisterd userRegisterd = new UserRegisterd(this);
        userRegisterd.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    public void register() {
        //implement business logic here:

        UserRegisterd userRegisterd = new UserRegisterd(this);
        userRegisterd.publishAfterCommit();
    }
}
//>>> DDD / Aggregate Root
