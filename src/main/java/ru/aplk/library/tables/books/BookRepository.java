package ru.aplk.library.tables.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findAllByAuthorId(Long authorId);
    List<Book> findAllByGenreId(Long genreId);
    @Query("" +
            "SELECT b, g, a " +
            "FROM Book b " +
            "JOIN Author a on b.authorId = a.id " +
            "JOIN Genre g on b.genreId = g.id " +
            "WHERE b.title = :title"
    )
    List<Object[]> findByTitleWithJoin(@Param("title") String title);
    @Query("" +
            "SELECT b, g, a " +
            "FROM Book b " +
            "JOIN Author a on b.authorId = a.id " +
            "JOIN Genre g on b.genreId = g.id " +
            "WHERE b.id = :id"
    )
    List<Object[]> findByIdWithJoin(@Param("id") Long id);

}
