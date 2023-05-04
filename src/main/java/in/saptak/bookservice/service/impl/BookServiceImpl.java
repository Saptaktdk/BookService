package in.saptak.bookservice.service.impl;

import in.saptak.bookservice.entity.BookDetails;
import in.saptak.bookservice.repository.BookRepository;
import in.saptak.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDetails> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookDetails> getBookById(Long id) throws ChangeSetPersister.NotFoundException {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookDetails> findByName(String name) {
        return bookRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<BookDetails> findByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    @Override
    public List<BookDetails> findByNameAndAuthor(String name, String author) {
        return bookRepository.findByNameContainingIgnoreCaseAndAuthorContainingIgnoreCase(name, author);
    }

    @Override
    public BookDetails addBook(BookDetails bookDetails) {
        return bookRepository.save(bookDetails);
    }

    @Override
    public BookDetails updateBook(Long id, BookDetails updatedBook) throws ChangeSetPersister.NotFoundException {
        Optional<BookDetails> existingBook = getBookById(id);

        if (existingBook.isEmpty()) return null;

        BookDetails foundBook = existingBook.get();

        foundBook.setName(updatedBook.getName());
        foundBook.setDescription(updatedBook.getDescription());
        foundBook.setAuthor(updatedBook.getAuthor());
        foundBook.setPrice(updatedBook.getPrice());

        return bookRepository.save(foundBook);
    }

    @Override
    public boolean deleteBook(Long id) {
        Optional<BookDetails> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return true;
        }
        else {
            return false;
        }
    }
}
