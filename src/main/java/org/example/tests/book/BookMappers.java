package org.example.tests.book;

import lombok.experimental.UtilityClass;
import org.example.tests.book.dto.BookResponse;
import org.example.tests.book.entities.BookEntity;

@UtilityClass
public class BookMappers {

    public static BookResponse map(BookEntity entity) {
        return new BookResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getPublicationYear()
        );
    }

}
