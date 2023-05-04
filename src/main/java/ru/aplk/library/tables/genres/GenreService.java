package ru.aplk.library.tables.genres;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aplk.library.additions.ServiceFunctions;
import ru.aplk.library.additions.types.ResponseWithStatus;
import ru.aplk.library.additions.types.StatusCode;
import ru.aplk.library.tables.authors.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<Genre>> findAll(){
        return ResponseWithStatus.create(200, genreRepository.findAll());
    }

    public ResponseWithStatus<Genre> findById(Long id){
        return serviceFunctions.findBy(id, genreRepository::findById);
    }

    public StatusCode save(Genre genre, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                genre,
                genre.getName(),
                genreRepository::findByName,
                genreRepository::save,
                request
        );
    }

}
