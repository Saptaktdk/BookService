package in.saptak.bookservice.repository;

import in.saptak.bookservice.entity.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<BookDetails, Long> {
   List<BookDetails> findByNameContainingIgnoreCase(String name);
   List<BookDetails> findByAuthorContainingIgnoreCase(String author);
   List<BookDetails> findByNameContainingIgnoreCaseAndAuthorContainingIgnoreCase(String name, String author);

}
