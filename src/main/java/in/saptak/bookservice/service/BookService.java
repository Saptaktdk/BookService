package in.saptak.bookservice.service;

import in.saptak.bookservice.entity.BookDetails;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDetails> getAllBooks();
    Optional<BookDetails> getBookById(Long id) throws ChangeSetPersister.NotFoundException;
    List<BookDetails> findByName(String name);
    List<BookDetails> findByAuthor(String author);
    List<BookDetails> findByNameAndAuthor(String name, String author);
    BookDetails addBook(BookDetails bookDetails);
    BookDetails updateBook(Long id, BookDetails bookDetails) throws ChangeSetPersister.NotFoundException;

    boolean deleteBook(Long id);

}
