package ru.edu.springdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.Category;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.language=?1 and b.category=?2")
    List<Book> findByLanguageAndCategory(String language, Category category);
}
