package booklend.infra;

import booklend.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/books")
@Transactional
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(
        value = "/books/create",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Book create(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateCommand createCommand
    ) throws Exception {
        System.out.println("##### /book/create  called #####");
        Book book = new Book();
        book.create(createCommand);
        bookRepository.save(book);
        return book;
    }

    @RequestMapping(
        value = "/books/cancel",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Book cancelApproved(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CancelApprovedCommand cancelApprovedCommand
    ) throws Exception {
        System.out.println("##### /book/cancelApproved  called #####");
        Book book = new Book();
        book.cancelApproved(cancelApprovedCommand);
        bookRepository.save(book);
        return book;
    }
}
//>>> Clean Arch / Inbound Adaptor
