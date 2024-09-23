package booklend.domain;

import booklend.UserApplication;
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

    private Date createDt;

    private Date updateDt;

    @PostPersist
    public void onPostPersist() {}

    public static UserRepository repository() {
        UserRepository userRepository = UserApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public void register(RegisterCommand registerCommand) {
        //implement business logic here:
        this.setCreateDt(new Date());
        this.setName(registerCommand.getName());
        UserRegisterd userRegisterd = new UserRegisterd(this);
        userRegisterd.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
