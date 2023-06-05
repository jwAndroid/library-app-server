package com.group.libraryapp.domain.book;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false)
    private String name;

    public Book(BookCreateRequest request) {
        if(request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException();
        }

        this.name = request.getName();
    }

    protected Book() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
