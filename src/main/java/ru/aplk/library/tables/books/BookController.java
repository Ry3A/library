package ru.aplk.library.tables.books;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aplk.library.additions.ControllerFunctions;
import ru.aplk.library.additions.types.ResponseWithStatus;
import ru.aplk.library.additions.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final ControllerFunctions controllerFunctions;
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Book>>> findAll(){
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping(params = "authorId")
    public ResponseEntity<ResponseWithStatus<List<Book>>> findAllByAuthorId(@Param("authorId") Long authorId){
        return ResponseEntity.ok(bookService.findAllByAuthorId(authorId));
    }

    @GetMapping(params = "genreId")
    public ResponseEntity<ResponseWithStatus<List<Book>>> findAllByGenreId(@Param("genreId") Long genreId){
        return ResponseEntity.ok(bookService.findAllByGenreId(genreId));
    }


    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<BookIsFull>> findById(@PathVariable Long id){
        return controllerFunctions.responseWithStatus(id, bookService::findById);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Book book, HttpServletRequest request){
        return controllerFunctions.statusCode(book, bookService::save, request);
    }


}
