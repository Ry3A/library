package ru.aplk.library.tables.books;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "books")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String year;
    @Column(name = "genre_id")
    private Long genreId;
    @Column(name = "author_id")
    private Long authorId;
}
