package org.example.tests.book;

import org.example.tests.book.entities.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("The index endpoint returns a list of all books.")
    public void testIndexEndpoint() throws Exception {
        List<BookEntity> books = List.of(
                BookEntity.create("Catcher in the Rye", 1951),
                BookEntity.create("Pride and Prejudice", 1813),
                BookEntity.create("The Hobbit", 1937)
        );

        repository.saveAll(books);

        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].title", contains(
                        "Catcher in the Rye", "Pride and Prejudice", "The Hobbit")))
                .andExpect(jsonPath("$[*].publicationYear", contains(1951, 1813, 1937)));
    }

    // etc
}
