package ru.aplk.library.tables.genres;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aplk.library.additions.ControllerFunctions;
import ru.aplk.library.additions.types.ResponseWithStatus;
import ru.aplk.library.additions.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {
    private final ControllerFunctions controllerFunctions;
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Genre>>> findAll(){
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Genre>> findById(@PathVariable Long id){
        return controllerFunctions.responseWithStatus(id,genreService::findById);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Genre genre, HttpServletRequest request){
        return controllerFunctions.statusCode(genre, genreService::save, request);
    }
}
