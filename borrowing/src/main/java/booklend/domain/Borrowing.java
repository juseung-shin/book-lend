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

    public static String REQUESTED = "REQUESTED";     // 대여요청
    public static String APPROVED = "APPROVED";       // 대여승인
    public static String REJECTED = "REJECTED";       // 대여거절
    public static String RETURNED = "RETURNED";       // 반납

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
    public static void updateStatus(BookRejected bookRejected) {
 
        repository().findById(bookRejected.getBorrowId()).ifPresent(borrowing->{
            borrowing.setRejectDt(bookRejected.getUpdateDt());
            borrowing.setStatus(bookRejected.getBorrowStatus());
            repository().save(borrowing);
         });
     
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(BookApproved bookApproved) {
   
        repository().findById(bookApproved.getBorrowId()).ifPresent(borrowing->{
            borrowing.setConfirmDt(bookApproved.getUpdateDt());
            borrowing.setStatus(bookApproved.getBorrowStatus());
            repository().save(borrowing);
         });
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
