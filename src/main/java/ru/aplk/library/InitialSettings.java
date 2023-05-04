package ru.aplk.library;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.aplk.library.authentication.routes.components.AuthService;
import ru.aplk.library.authentication.routes.dto.RegisterRequestDTO;
import ru.aplk.library.users.Role;

@Component
public class InitialSettings {
    public InitialSettings(
            @Value("${admin.firstname}") String adminFirstname,
            @Value("${admin.lastname}") String adminLastname,
            @Value("${admin.email}") String adminEmail,
            @Value("${admin.password}") String adminPassword,
            AuthService authService
    ) {
        authService.register(
                RegisterRequestDTO.builder()
                        .firstname(adminFirstname)
                        .lastname(adminLastname)
                        .email(adminEmail)
                        .password(adminPassword)
                        .build(),
                Role.ADMIN
        );
    }
}
