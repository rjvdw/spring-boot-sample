package org.example.tests.book.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Book")
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Integer publicationYear;

    public static BookEntity create(String title, int publicationYear) {
        BookEntity entity = new BookEntity();
        entity.setTitle(title);
        entity.setPublicationYear(publicationYear);
        return entity;
    }

}
