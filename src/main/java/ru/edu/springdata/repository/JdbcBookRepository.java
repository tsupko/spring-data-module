package ru.edu.springdata.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcBookRepository implements BookRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final JdbcTemplate jdbcTemplate;

    public JdbcBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAll(String language, String category) {
        return jdbcTemplate.query(
                "select * from Book where language=? and category=?",
                this::mapRowToBook,
                language, category
        );
    }

    private Book mapRowToBook(ResultSet row, int rowNum) throws SQLException {
        return new Book(
                row.getLong("id"),
                row.getString("name"),
                row.getString("language"),
                row.getString("category")
        );
    }

    @Override
    public Book save(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        int updated = jdbcTemplate.update(conn -> {
            var ps = conn.prepareStatement(
                    "insert into Book (name, language, category) values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, book.getName());
            ps.setString(2, book.getLanguage());
            ps.setString(3, book.getCategory());
            return ps;
        }, keyHolder);
        log.info("Rows inserted: {}", updated);
        book.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return book;
    }

    @Override
    public void update(Book book) {
        int updated = jdbcTemplate.update(
                "update Book set name=?, language=?, category=? where id=?",
                book.getName(), book.getLanguage(), book.getCategory(), book.getId()
        );
        log.info("Rows updated: {}", updated);
    }

    @Override
    public void delete(Long id) {
        int updated = jdbcTemplate.update("delete from Book where id=?", id);
        log.info("Rows deleted: {}", updated);
    }
}
