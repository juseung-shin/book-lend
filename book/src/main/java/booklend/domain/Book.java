package booklend.domain;

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


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String title;
    
    
    
    
    private String status;
    
    
    
    
    private Date createDt;
    
    
    
    
    private Date updateDt;
    
    
    
    
    private Long borrowid;
    
    
    
    
    private String borrowStatus;

    @PostPersist
    public void onPostPersist(){


        BookApproved bookApproved = new BookApproved(this);
        bookApproved.publishAfterCommit();

    
    }

    public static BookRepository repository(){
        BookRepository bookRepository = BookApplication.applicationContext.getBean(BookRepository.class);
        return bookRepository;
    }



//<<< Clean Arch / Port Method
    public void create(CreateCommand createCommand){
        
        //implement business logic here:
        

    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void cancelApproved(CancelApprovedCommand cancelApprovedCommand){
        
        //implement business logic here:
        
        BookCancled bookCancled = new BookCancled(this);
        bookCancled.publishAfterCommit();


        booklend.external.BookQuery bookQuery = new booklend.external.BookQuery();
        BookApplication.applicationContext
            .getBean(booklend.external.Service.class)
            .( bookQuery);
    }
//>>> Clean Arch / Port Method

//<<< Clean Arch / Port Method
    public static void ifApproveBorrow(BookBorrowed bookBorrowed){
        
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        BookApproved bookApproved = new BookApproved(book);
        bookApproved.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(bookBorrowed.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            BookApproved bookApproved = new BookApproved(book);
            bookApproved.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
