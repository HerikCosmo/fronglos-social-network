package cosmo.herik.fronglos.controller;

import cosmo.herik.fronglos.docs.AuthControllerDocs;
import cosmo.herik.fronglos.dto.request.LoginRequest;
import cosmo.herik.fronglos.dto.request.RegisterUserRequest;
import cosmo.herik.fronglos.dto.response.LoginResponse;
import cosmo.herik.fronglos.dto.response.RegisterUserResponse;
import cosmo.herik.fronglos.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterUserResponse register(@Valid @RequestBody RegisterUserRequest request) {
        return authService.register(request);
    }
}
