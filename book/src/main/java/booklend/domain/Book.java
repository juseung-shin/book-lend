package booklend.domain;

import booklend.domain.BookRejected;
import booklend.domain.BookApproved;
import booklend.BookApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Book_table")
@Data
//<<< DDD / Aggregate Root
public class Book  {

    public static String UNAVAILABLE = "UNAVAILABLE"; // 대여불가능
    public static String AVAILABLE = "AVAILABLE";     // 대여가능
    public static String BORROWED = "BORROWED";       // 대여중
    
    public static String REQUESTED = "REQUESTED";     // 대여요청
    public static String APPROVED = "APPROVED";       // 대여승인
    public static String REJECTED = "REJECTED";       // 대여거절
    public static String RETURNED = "RETURNED";       // 반납


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
    private Long id;
    private String title;
    private String status;
    private Date createDt;
    private Date updateDt;
    private Long borrowId;
    
    
    
    
    private String borrowStatus;

    @PostPersist
    public void onPostPersist(){
        // if (this.getStatus().equals(AVAILABLE)) {
        //     this.setBorrowStatus(APPROVED);
        //     BookApproved bookApproved = new BookApproved(this);
        //     bookApproved.publishAfterCommit();
        // } else {
        //     this.setBorrowStatus(REJECTED);
        //     BookRejected bookRejected = new BookRejected(this);
        //     bookRejected.publishAfterCommit();    
        // }
    }

    public static BookRepository repository(){
        BookRepository bookRepository = BookApplication.applicationContext.getBean(BookRepository.class);
        return bookRepository;
    }



//<<< Clean Arch / Port Method
    public void create(CreateCommand createCommand){
        
        //implement business logic here:
        this.setCreateDt(new Date());
        this.setTitle(createCommand.getTitle());
        this.setStatus(AVAILABLE);

    }

//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void update(UpdateCommand updateCommand){
        
        //implement business logic here:        
        BookCancled bookCancled = new BookCancled(this);
        bookCancled.publishAfterCommit();

    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void approveBook(BookBorrowed bookBorrowed){
            
        repository().findById(bookBorrowed.getBookId()).ifPresent(book->{
            book.setUpdateDt(new Date());
            book.setBorrowId(bookBorrowed.getId());
            if (book.getStatus().equals(book.AVAILABLE)) {
                book.setBorrowStatus(APPROVED);
                book.setStatus(BORROWED);
                BookApproved bookApproved = new BookApproved(book);
                bookApproved.publishAfterCommit();
            } else {
                book.setBorrowStatus(REJECTED);
                BookRejected bookRejected = new BookRejected(book);
                bookRejected.publishAfterCommit();
            }
        
        });       
        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void returnBook(BookReturned bookReturned){
        
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        */

     
        
        repository().findById(bookReturned.getBookId()).ifPresent(book->{
            if (book.getStatus().equals(book.BORROWED)) {
                book.setStatus(book.AVAILABLE);
                repository().save(book);
            }

         });
     

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
