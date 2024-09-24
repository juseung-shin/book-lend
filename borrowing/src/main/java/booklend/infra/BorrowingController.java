package booklend.infra;

import booklend.domain.*;

import java.util.Date;
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
// @RequestMapping(value="/borrowings")
@Transactional
public class BorrowingController {

    @Autowired
    BorrowingRepository borrowingRepository;

    @RequestMapping(
        value = "/borrowings/request",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Borrowing request(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RequestCommand requestCommand
    ) throws Exception {
        System.out.println("##### /borrowing/request  called #####");
        System.out.println("RequestCommand : " + requestCommand.toString());
        Borrowing borrowing = new Borrowing();
        borrowing.setBookId(requestCommand.getBookId());
        borrowing.setUserId(requestCommand.getUserId());
        borrowing.setCreateDt(new Date());
        borrowing.setStatus(borrowing.REQUESTED);   
        borrowingRepository.save(borrowing);
        borrowing.request(requestCommand);

        return borrowing;
    }

    @RequestMapping(
        value = "/borrowings/retrun",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Borrowing retrun(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RetrunCommand retrunCommand
    ) throws Exception {
        System.out.println("##### /borrowing/retrun  called #####");
        Borrowing borrowing = new Borrowing();
        borrowing.retrun(retrunCommand);
        borrowingRepository.save(borrowing);
        return borrowing;
    }
}
//>>> Clean Arch / Inbound Adaptor
