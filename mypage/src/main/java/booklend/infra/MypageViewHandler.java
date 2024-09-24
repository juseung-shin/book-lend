package booklend.infra;

import booklend.config.kafka.KafkaProcessor;
import booklend.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MypageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookBorrowed_then_CREATE_1(
        @Payload BookBorrowed bookBorrowed
    ) {
        try {
            if (!bookBorrowed.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setUserId(bookBorrowed.getUserId());
            mypage.setBookId(bookBorrowed.getBookId());
            mypage.setBorrowId(bookBorrowed.getId());
            mypage.setStatus(bookBorrowed.getStatus());
            // view 레파지 토리에 save
            mypageRepository.save(mypage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookApproved_then_UPDATE_1(
        @Payload BookApproved bookApproved
    ) {
        try {
            if (!bookApproved.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByBorrowId(
                bookApproved.getBorrowId()
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setStatus(bookApproved.getBorrowStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookRejected_then_UPDATE_2(
        @Payload BookRejected bookRejected
    ) {
        try {
            if (!bookRejected.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByBorrowId(
                bookRejected.getBorrowId()
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setStatus(bookRejected.getBorrowStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
