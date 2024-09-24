package booklend.infra;

import booklend.domain.*;
import booklend.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {

//<<< DDD / CQRS
    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookBorrowed_then_CREATE_1 (@Payload BookBorrowed bookBorrowed) {
        try {

            if (!bookBorrowed.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setUserid(Long.valueOf(bookBorrowed.getUserId()));
            mypage.setBookId(Long.valueOf(bookBorrowed.getBookId()));
            mypage.setBorrowId(bookBorrowed.getId());
            mypage.setStatus(Long.valueOf(bookBorrowed.getStatus()));
            // view 레파지 토리에 save
            mypageRepository.save(mypage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookApproved_then_UPDATE_1(@Payload BookApproved bookApproved) {
        try {
            if (!bookApproved.validate()) return;
                // view 객체 조회

                List<Mypage> mypageList = mypageRepository.findByBorrowId(bookApproved.getBorrowId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setStatus(Long.valueOf(bookApproved.getStatus()));
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void when_then_UPDATE_(@Payload  ) {
        try {
            if (!.validate()) return;
                // view 객체 조회

                List<Mypage> mypageList = mypageRepository.findByBorrowId(.getBorrowid());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setStatus(Long.valueOf(.getStatus()));
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


//>>> DDD / CQRS
}

