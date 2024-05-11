package ru.edu.springdata.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность описывающая книги.
 * Между книгами и категориями связь один ко многим.
 * Книга может быть только в одной категории. В одной категории может быть множество книг.
 * <p>
 * Между книгами и авторами связь многие ко многим.
 * Между авторами и адресами свзяь один к одному
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String language;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String title, String language, Category category, boolean active) {
        this.title = title;
        this.language = language;
        this.category = category;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", category=" + category +
                ", active=" + active +
                ", authors=" + authors +
                '}';
    }
}
