package cosmo.herik.fronglos.controller;

import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public AuthUserProfileResponse me(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return userService.getAuthUserProfile(authorizationHeader);
    }
}
