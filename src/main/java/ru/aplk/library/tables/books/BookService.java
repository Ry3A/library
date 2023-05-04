package ru.aplk.library.tables.books;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aplk.library.additions.ServiceFunctions;
import ru.aplk.library.additions.types.ResponseWithStatus;
import ru.aplk.library.additions.types.StatusCode;
import ru.aplk.library.tables.authors.Author;
import ru.aplk.library.tables.authors.AuthorRepository;
import ru.aplk.library.tables.genres.Genre;
import ru.aplk.library.tables.genres.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ServiceFunctions serviceFunctions;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private BookIsFull isFull(List<Object[]> entitiesArr) {
        Object[] entities = entitiesArr.get(0);
        Book book = (Book) entities[0];
        Genre genre = (Genre) entities[1];
        Author author = (Author) entities[2];

        return BookIsFull.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(author)
                .genre(genre)
                .build();
    }
    public boolean notFull(Book book) {
       if(!authorRepository.existsById(book.getAuthorId())){
           return true;
       }
       if(!genreRepository.existsById(book.getGenreId())){
           return true;
       }
       return false;
    }

    public ResponseWithStatus<List<Book>> findAll(){
        return ResponseWithStatus.create(200, bookRepository.findAll());
    }

    public ResponseWithStatus<List<Book>> findAllByAuthorId(Long authorId){
        return ResponseWithStatus.create(200, bookRepository.findAllByAuthorId(authorId));
    }

    public ResponseWithStatus<List<Book>> findAllByGenreId(Long genreId){
        return ResponseWithStatus.create(200, bookRepository.findAllByGenreId(genreId));
    }

    public ResponseWithStatus<BookIsFull> findById(Long id){
        return serviceFunctions.findByWithJoin(
                id,
                bookRepository::findByIdWithJoin,
                this::isFull
        );
    }

    public ResponseWithStatus<BookIsFull> findByTitle(String title){
        return serviceFunctions.findByWithJoin(
                title,
                bookRepository::findByTitleWithJoin,
                this::isFull
        );
    }

    public StatusCode save(Book book, HttpServletRequest request){
        return serviceFunctions.saveWithCheckFieldsWithAuth(
                book,
                this::notFull,
                bookRepository::save,
                request
        );
    }

}
