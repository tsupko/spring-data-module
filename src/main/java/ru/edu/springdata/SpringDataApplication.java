package ru.edu.springdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.repository.BookRepository;

import java.util.List;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final BookRepository bookRepository;

	public SpringDataApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var book = new Book("b", "en", "it");
		// save
		Book savedBook = bookRepository.save(book);
		log.info("Book saved: {}", savedBook);
		// findAll
		List<Book> allBooks = bookRepository.findAll("en", "it");
		var sb = new StringBuilder();
		allBooks.forEach(sb::append);
		log.info("Books by language=\"en\" and category=\"it\": {}", sb);
		// delete
		bookRepository.delete(allBooks.get(0).getId());
		// findAll
		allBooks = bookRepository.findAll("en", "it");
		sb = new StringBuilder();
		allBooks.forEach(sb::append);
		log.info("Books by language=\"en\" and category=\"it\": {}", sb);
		// findAll
		allBooks = bookRepository.findAll("ru", "history");
		sb = new StringBuilder();
		allBooks.forEach(sb::append);
		log.info("Books by language=\"ru\" and category=\"history\": {}", sb);
		// update
		book = allBooks.get(0);
		book.setName("c");
		book.setLanguage("en");
		book.setCategory("it");
		bookRepository.update(book);
		// findAll
		allBooks = bookRepository.findAll("en", "it");
		sb = new StringBuilder();
		allBooks.forEach(sb::append);
		log.info("Books by language=\"en\" and category=\"it\": {}", sb);
	}
}
