package in.saptak.bookservice.controller;

import in.saptak.bookservice.entity.BookDetails;
import in.saptak.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<BookDetails>> getAllBooks() {
        List<BookDetails> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookDetails>> getBookById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Optional<BookDetails> book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/filter")
    public List<BookDetails> getBooksByFilter(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "author", required = false) String author
    ) {
        if (name != null && author != null) {
            // search by both name and author
            return  bookService.findByNameAndAuthor(name, author);
        }else if (name != null) {
            // Search by name only
            return  bookService.findByName(name);
        } else if (author != null) {
            // Search by author only
            return  bookService.findByAuthor(author);
        } else {
            // Return all books if no parameters are provided
            return  bookService.getAllBooks();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BookDetails> addBook(@RequestBody BookDetails book) {
        BookDetails savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDetails> updateBookById(@PathVariable Long id, @RequestBody BookDetails book) throws ChangeSetPersister.NotFoundException {
        BookDetails updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        boolean deletedBook = bookService.deleteBook(id);
        if (deletedBook) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
