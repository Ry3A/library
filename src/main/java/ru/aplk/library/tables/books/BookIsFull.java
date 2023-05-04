package ru.aplk.library.tables.books;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.aplk.library.tables.authors.Author;
import ru.aplk.library.tables.genres.Genre;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookIsFull {
    private Long id;
    private String title;
    private String year;
    private Author author;
    private Genre genre;
}
