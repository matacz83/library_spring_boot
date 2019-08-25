package pl.sda.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.library.model.Book;
import pl.sda.library.service.OrderService;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

@RestController
public class BookController {

    private final OrderService orderService;

    public BookController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/books", produces = "application/json")
    public Set<Book> getBooks(@RequestParam(required = false) String title) {
        return orderService.getBook(title);
    }

    @GetMapping(value = "/books/order/{id}", produces = "application/json")
    public ResponseEntity<Book> rentBook(@PathVariable Long id) {
        Optional<Book> book = orderService.rentBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "book/add", consumes = "application/json")
    public ResponseEntity<Long> addBook(@RequestBody Book book) {
        Book addedBook = orderService.addBook(book);
        return new ResponseEntity<>(addedBook.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/book/remove/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable Long id) {
        boolean deleted = orderService.removeBook(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
