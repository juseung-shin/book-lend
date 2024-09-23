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
    public void wheneverBookBorrowed_IfApproveBorrow(
        @Payload BookBorrowed bookBorrowed
    ) {
        BookBorrowed event = bookBorrowed;
        System.out.println(
            "\n\n##### listener IfApproveBorrow : " + bookBorrowed + "\n\n"
        );

        // Sample Logic //
        Book.ifApproveBorrow(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
