package ru.aplk.library;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aplk.library.authentication.routes.dto.AuthResponseDTO;
import ru.aplk.library.authentication.routes.dto.RegisterRequestDTO;
import ru.aplk.library.tables.authors.AuthorRepository;
import ru.aplk.library.users.Role;
import ru.aplk.library.users.User;
import ru.aplk.library.authentication.routes.components.AuthController;
import ru.aplk.library.users.UserRepo;

@Controller
@RequiredArgsConstructor
public class WebController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/auth/register")
    public String saveUser(@ModelAttribute User user) {
        userRepo.save(user);
        return "redirect:/auth/login"; // перенаправление на страницу успеха
    }

    @GetMapping("/auth/register")
    public String registerPage(@ModelAttribute("user") User user){
        return "auth/register";
    }


}
