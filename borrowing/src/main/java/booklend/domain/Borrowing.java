package booklend.domain;

import booklend.BorrowingApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Borrowing_table")
@Data
//<<< DDD / Aggregate Root
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long bookId;

    private String status;

    private Date createDt;

    private Date returnDt;

    private Date rejectDt;

    private Date confirmDt;

    @PostPersist
    public void onPostPersist() {}

    public static BorrowingRepository repository() {
        BorrowingRepository borrowingRepository = BorrowingApplication.applicationContext.getBean(
            BorrowingRepository.class
        );
        return borrowingRepository;
    }

    //<<< Clean Arch / Port Method
    public void request(RequestCommand requestCommand) {
        //implement business logic here:

        BookBorrowed bookBorrowed = new BookBorrowed(this);
        bookBorrowed.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void retrun(RetrunCommand retrunCommand) {
        //implement business logic here:

        BookReturned bookReturned = new BookReturned(this);
        bookReturned.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void resultApprove(BookApproved bookApproved) {
        //implement business logic here:

        /** Example 1:  new item 
        Borrowing borrowing = new Borrowing();
        repository().save(borrowing);

        */

        /** Example 2:  finding and process
        
        repository().findById(bookApproved.get???()).ifPresent(borrowing->{
            
            borrowing // do something
            repository().save(borrowing);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
