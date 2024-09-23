package booklend.infra;

import booklend.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class BorrowingHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Borrowing>> {

    @Override
    public EntityModel<Borrowing> process(EntityModel<Borrowing> model) {
        return model;
    }
}
