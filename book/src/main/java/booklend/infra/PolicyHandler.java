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
    BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookBorrowed'"
    )
    public void wheneverBookBorrowed_ApproveBook(
        @Payload BookBorrowed bookBorrowed
    ) {
        BookBorrowed event = bookBorrowed;
        System.out.println(
            "\n\n##### listener ApproveBook : " + bookBorrowed + "\n\n"
        );

        // Sample Logic //
        Book.approveBook(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookReturned'"
    )
    public void wheneverBookReturned_ReturnBook(
        @Payload BookReturned bookReturned
    ) {
        BookReturned event = bookReturned;
        System.out.println(
            "\n\n##### listener ReturnBook : " + bookReturned + "\n\n"
        );

        // Sample Logic //
        Book.returnBook(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
