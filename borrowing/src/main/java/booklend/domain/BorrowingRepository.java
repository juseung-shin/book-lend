package booklend.domain;

import booklend.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "borrowings",
    path = "borrowings"
)
public interface BorrowingRepository
    extends PagingAndSortingRepository<Borrowing, Long> {}
