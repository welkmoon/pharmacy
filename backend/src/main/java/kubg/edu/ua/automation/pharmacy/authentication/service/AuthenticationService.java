package kubg.edu.ua.automation.pharmacy.authentication.service;

import kubg.edu.ua.automation.pharmacy.annotation.Public;
import kubg.edu.ua.automation.pharmacy.authentication.dto.AuthenticationRequest;
import kubg.edu.ua.automation.pharmacy.dto.StringValueDto;
import kubg.edu.ua.automation.pharmacy.entity.MyUser;
import kubg.edu.ua.automation.pharmacy.exception.NotFoundException;
import kubg.edu.ua.automation.pharmacy.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final MyUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Public
    public StringValueDto authenticate(AuthenticationRequest request) {
        MyUser user = getUserByRequest(request);
        String token = jwtService.generateToken(user);
        return new StringValueDto(token);
    }

    private MyUser getUserByRequest(AuthenticationRequest request) {
        return userService.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .filter(user -> user.isAccountNonExpired() && user.isCredentialsNonExpired() && user.isEnabled())
                .orElseThrow(() -> new NotFoundException("No user found with current credentials"));
    }
}
