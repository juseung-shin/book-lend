package booklend.domain;

import booklend.BorrowingApplication;
import booklend.domain.BookBorrowed;
import booklend.domain.BookReturned;
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

    private String userId;

    private String bookId;

    private String status;

    private Date createDt;

    private Date returnDt;

    private Date rejectDt;

    private Date confirmDt;

    @PostPersist
    public void onPostPersist() {
        BookBorrowed bookBorrowed = new BookBorrowed(this);
        bookBorrowed.publishAfterCommit();

        BookReturned bookReturned = new BookReturned(this);
        bookReturned.publishAfterCommit();
    }

    public static BorrowingRepository repository() {
        BorrowingRepository borrowingRepository = BorrowingApplication.applicationContext.getBean(
            BorrowingRepository.class
        );
        return borrowingRepository;
    }

    public void request() {
        //implement business logic here:

        BookBorrowed bookBorrowed = new BookBorrowed(this);
        bookBorrowed.publishAfterCommit();
    }

    public void retrun() {
        //implement business logic here:

        BookReturned bookReturned = new BookReturned(this);
        bookReturned.publishAfterCommit();
    }

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
    //<<< Clean Arch / Port Method
    public static void cancelApprove(BookCancled bookCancled) {
        //implement business logic here:

        /** Example 1:  new item 
        Borrowing borrowing = new Borrowing();
        repository().save(borrowing);

        */

        /** Example 2:  finding and process
        
        repository().findById(bookCancled.get???()).ifPresent(borrowing->{
            
            borrowing // do something
            repository().save(borrowing);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
