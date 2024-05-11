package ru.edu.springdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.edu.springdata.model.Address;
import ru.edu.springdata.model.Author;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.Category;
import ru.edu.springdata.repo.BookRepo;

import java.util.List;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final BookRepo repo;

    public SpringDataApplication(BookRepo repo) {
        this.repo = repo;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // создаем автора и его адрес
        var author = new Author("Роберт", "Мартин");
        var address = new Address("Нью-Йорк", "Даунинг-стрит");
        // назначаем автору адрес
        author.setAddress(address);

        // создаем категорию и книгу в этой категории
        var category = new Category("IT");
        var book = new Book("Идеальный программист", "ru", category, true);
        // назначаем книге список авторов
        book.setAuthors(List.of(author));

        // сохраняем книгу
        repo.save(book);
        // получаем все книги по языку и категории
        List<Book> all = repo.findByLanguageAndCategory("ru", category);
        System.out.println(all);

        if (!all.isEmpty()) {
            // меняем заголовок книги
            all.get(0).setTitle("Чистая архитектура");
            repo.save(all.get(0));
        }

        // получаем все книги
        all = repo.findAll();
        System.out.println(all);

        if (!all.isEmpty()) {
            // удаляем книгу
            repo.deleteById(all.get(0).getId());
        }

        // получаем все книги
        all = repo.findAll();
        System.out.println(all);
    }
}
