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


        BookRejected bookRejected = new BookRejected(this);
        bookRejected.publishAfterCommit();



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
    public void update(UpdateCommand updateCommand){
        
        //implement business logic here:
        

        booklend.external.BookQuery bookQuery = new booklend.external.BookQuery();
        BookApplication.applicationContext
            .getBean(booklend.external.Service.class)
            .( bookQuery);
    }
//>>> Clean Arch / Port Method

//<<< Clean Arch / Port Method
    public static void approveBook(BookBorrowed bookBorrowed){
        
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        BookRejected bookRejected = new BookRejected(book);
        bookRejected.publishAfterCommit();
        BookApproved bookApproved = new BookApproved(book);
        bookApproved.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(bookBorrowed.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            BookRejected bookRejected = new BookRejected(book);
            bookRejected.publishAfterCommit();
            BookApproved bookApproved = new BookApproved(book);
            bookApproved.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void returnBook(BookReturned bookReturned){
        
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        */

        /** Example 2:  finding and process
        
        repository().findById(bookReturned.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
