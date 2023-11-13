package org.example.tests.book;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.tests.book.dto.BookRequest;
import org.example.tests.book.dto.BookResponse;
import org.example.tests.book.entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookResponse> list() {
        return bookRepository
                .findAll()
                .stream()
                .map(BookMappers::map)
                .toList();
    }

    public Optional<BookResponse> get(long id) {
        return bookRepository
                .findById(id)
                .map(BookMappers::map);
    }

    public BookResponse create(BookRequest book) {
        BookEntity entity = BookEntity.create(book.title(), book.publicationYear());
        bookRepository.save(entity);
        return BookMappers.map(entity);
    }

    @Transactional
    public Optional<BookResponse> update(long id, BookRequest book) {
        Optional<BookEntity> entityOptional = bookRepository.findById(id);

        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }

        BookEntity entity = entityOptional.get();
        entity.setTitle(book.title());
        entity.setPublicationYear(book.publicationYear());
        bookRepository.save(entity);

        return Optional.of(BookMappers.map(entity));
    }

    public void delete(long id) {
        bookRepository.deleteById(id);
    }

}
