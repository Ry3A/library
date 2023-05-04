package ru.aplk.library.tables.authors;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aplk.library.additions.ServiceFunctions;
import ru.aplk.library.additions.types.ResponseWithStatus;
import ru.aplk.library.additions.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<Author>> findAll(){
        return ResponseWithStatus.create(200, authorRepository.findAll());
    }

    public ResponseWithStatus<Author> findById(Long id){
        return serviceFunctions.findBy(id, authorRepository::findById);
    }

    public StatusCode save(Author author, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                author,
                author.getFullName(),
                authorRepository::findByFullName,
                authorRepository::save,
                request
        );
    }
}
