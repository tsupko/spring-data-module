package ru.edu.springdata.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Категория книг. На пр. Health, History, IT
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
