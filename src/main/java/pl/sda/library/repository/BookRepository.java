package pl.sda.library.repository;

import org.springframework.stereotype.Repository;
import pl.sda.library.model.Book;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    private Set<Book> books = listOfBooks();

    private Set<Book> listOfBooks() {
        Book book1 = new Book(1L, "Paris B.A.", "Pozwól mi umrzeć");
        Book book2 = new Book(2L, "Marcel Moss", "Nie odpisuj");
        Book book3 = new Book(3L, "Watson Sue", "Nasze małe kłamstwa");
        Book book4 = new Book(4L, "Sandie Jones", "Rywalka");
        Book book5 = new Book(5L, "David Lagercrantz", "Ta, która musi umrzeć");


        return new HashSet<>(Arrays.asList(book1, book2, book3, book4, book5));
    }

    public Set<Book> getBooks(String title) {
        if (title == null) {
            return books;
        } else {
            return books.stream()
                    .filter(book -> book.getTitle().equals(title))
                    .collect(Collectors.toSet());
        }
    }

    public Optional<Book> rentBook(Long id, LocalDate returnDate) {
        Optional<Book> bookOptional = books.stream()
                .filter(book -> book.getId().equals(id))
                .filter(book -> book.getReturnDate() == null)
                .findAny();
        bookOptional.ifPresent(book -> book.setReturnDate(returnDate));
        return bookOptional;
    }

    public Book addBook(Book book) {
        book.setId((nextId()));
        books.add(book);
        return book;
    }

    private Long nextId() {
        return books.stream()
                .mapToLong(Book::getId)
                .max().getAsLong() + 1;
    }

    public boolean removeBook(Long id) {
        Optional<Book> bookById = books.stream().filter(actualBook -> actualBook.getId().equals(id)).findAny();
        boolean present = bookById.isPresent();
        if (present) {
            Book bookToRemove = bookById.get();
            books.remove(bookToRemove);
        }
        return present;
    }
}

