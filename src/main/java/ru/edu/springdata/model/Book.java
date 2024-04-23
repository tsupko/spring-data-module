package ru.edu.springdata.model;

import java.util.Objects;

public class Book {

    private Long id;
    private String name;
    private String language;
    private String category; // history, it, health etc...

    public Book(Long id, String name, String language, String category) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.category = category;
    }

    public Book(String name, String language, String category) {
        this.name = name;
        this.language = language;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getName(), book.getName()) && Objects.equals(getLanguage(), book.getLanguage()) && Objects.equals(getCategory(), book.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLanguage(), getCategory());
    }

    @Override
    public String toString() {
        return "\nBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
