package ru.edu.springdata.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность описывающая авторов книг
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
