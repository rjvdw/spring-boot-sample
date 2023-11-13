package org.example.tests.book;


import org.example.tests.book.dto.BookResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerTest {

    BookService bookService = mock(BookService.class);
    BookController bookController = new BookController(bookService);

    @Test
    @DisplayName("The index endpoint returns a list of all books.")
    public void testIndex() {
        List<BookResponse> books = List.of(
                new BookResponse(1, "Catcher in the Rye", 1951),
                new BookResponse(2, "Pride and Prejudice", 1813),
                new BookResponse(3, "The Hobbit", 1937)
        );

        when(bookService.list()).thenReturn(books);

        List<BookResponse> actual = bookController.index();

        assertThat(actual).containsExactlyElementsOf(books);
    }

    // etc
}
