package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    }

}
