package org.example.tests.book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.tests.book.dto.BookRequest;
import org.example.tests.book.dto.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public List<BookResponse> index() {
        return bookService.list();
    }

    @GetMapping("/api/books/{id}")
    public Optional<BookResponse> get(@PathVariable long id) {
        return bookService.get(id);
    }

    @PostMapping("/api/books")
    public ResponseEntity<Void> create(@RequestBody BookRequest book) {
        BookResponse response = bookService.create(book);
        return ResponseEntity
                .created(URI.create("/api/books/" + response.id()))
                .build();
    }

    @PutMapping("/api/books/{id}")
    public Optional<BookResponse> update(@PathVariable long id, @RequestBody BookRequest book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/api/books/{id}")
    public void delete(@PathVariable long id) {
        bookService.delete(id);
    }
}
