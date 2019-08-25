package pl.sda.library.service;

import org.springframework.stereotype.Service;
import pl.sda.library.model.Book;
import pl.sda.library.repository.BookRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    private final BookRepository bookRepository;

    public OrderService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Set<Book> getBook(String title) {
        return bookRepository.getBooks(title);
    }

    public Optional<Book> rentBook(Long id) {
        return bookRepository.rentBook(id, LocalDate.now().plusDays(30));
    }

    public Book addBook(Book book) {
        return bookRepository.addBook(book);
    }

    public boolean removeBook(Long id){
        return bookRepository.removeBook(id);
    }
}
