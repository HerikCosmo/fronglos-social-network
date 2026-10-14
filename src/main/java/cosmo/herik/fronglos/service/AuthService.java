package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.config.JwtService;
import cosmo.herik.fronglos.dto.request.LoginRequest;
import cosmo.herik.fronglos.dto.request.RegisterUserRequest;
import cosmo.herik.fronglos.dto.response.LoginResponse;
import cosmo.herik.fronglos.dto.response.RegisterUserResponse;
import cosmo.herik.fronglos.entity.User;
import cosmo.herik.fronglos.exception.ResourceAlreadyExistsException;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterUserResponse register(RegisterUserRequest request) {
        if(userRepository.existsByUsername(request.username())) {
            throw new ResourceAlreadyExistsException("Username is already taken");
        }

        if(userRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("Email is already registered");
        }

        var user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .displayName(request.displayName())
                .build();

        userRepository.save(user);

        return new RegisterUserResponse(user.getPublicUsername(), user.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findUserByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException(request.email()));

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}
