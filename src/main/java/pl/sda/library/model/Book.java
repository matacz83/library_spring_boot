package pl.sda.library.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private Long id;
    private String author;
    private String title;
    private LocalDate returnDate;

    public Book(Long id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Book() {
    }

    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter
    public LocalDate getReturnDate() {
        return returnDate;
    }

    @JsonIgnore
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(returnDate, book.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, returnDate);
    }


}
