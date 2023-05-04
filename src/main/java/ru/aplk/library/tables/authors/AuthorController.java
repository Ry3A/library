package ru.aplk.library.tables.authors;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aplk.library.additions.ControllerFunctions;
import ru.aplk.library.additions.types.ResponseWithStatus;
import ru.aplk.library.additions.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final ControllerFunctions controllerFunctions;
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Author>>> findAll(){
        return ResponseEntity.ok(authorService.findAll());
    };

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Author>> findById(@PathVariable Long id){
        return controllerFunctions.responseWithStatus(id, authorService::findById);
    };

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Author author, HttpServletRequest request){
        return controllerFunctions.statusCode(author, authorService::save, request);
    };
}
