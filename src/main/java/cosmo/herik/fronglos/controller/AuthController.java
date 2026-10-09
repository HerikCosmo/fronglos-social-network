package cosmo.herik.fronglos.controller;

import cosmo.herik.fronglos.dto.request.UserRegisterRequest;
import cosmo.herik.fronglos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
    }


}
