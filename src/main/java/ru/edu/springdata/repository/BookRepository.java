package ru.edu.springdata.repository;

import ru.edu.springdata.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll(String language, String category);

    Book save(Book book);

    void update(Book book);

    void delete(Long id);
}
