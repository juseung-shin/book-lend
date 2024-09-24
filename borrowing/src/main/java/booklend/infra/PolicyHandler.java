package booklend.infra;

import booklend.config.kafka.KafkaProcessor;
import booklend.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    BorrowingRepository borrowingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookRejected'"
    )
    public void wheneverBookRejected_UpdateStatus(
        @Payload BookRejected bookRejected
    ) {
        BookRejected event = bookRejected;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + bookRejected + "\n\n"
        );

        // Sample Logic //
        Borrowing.updateStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookApproved'"
    )
    public void wheneverBookApproved_UpdateStatus(
        @Payload BookApproved bookApproved
    ) {
        BookApproved event = bookApproved;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + bookApproved + "\n\n"
        );

        // Sample Logic //
        Borrowing.updateStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
