package production.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import production.model.User;
import production.web.dto.UserRegistrationDto;

import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<User> addCommission(String email1, String email2);

    User currentUser();

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
