package kubg.edu.ua.automation.pharmacy.service;

import kubg.edu.ua.automation.pharmacy.annotation.Public;
import kubg.edu.ua.automation.pharmacy.authentication.dto.MyUserRequest;
import kubg.edu.ua.automation.pharmacy.dto.UserProfileDto;
import kubg.edu.ua.automation.pharmacy.entity.MyUser;
import kubg.edu.ua.automation.pharmacy.exception.BadRequestException;
import kubg.edu.ua.automation.pharmacy.exception.NotFoundException;
import kubg.edu.ua.automation.pharmacy.mapper.MyUserMapper;
import kubg.edu.ua.automation.pharmacy.repository.MyUserRepository;
import kubg.edu.ua.automation.pharmacy.util.EmailValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepository repository;
    private final MyUserMapper mapper;


    @Public
    public MyUser getByEmail(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("No employee found by email: %1$s", email)));
    }

    @Public
    public void create(MyUserRequest request) {
        validateMyUserRequest(request);
        MyUser user = mapper.toEntity(request);
        repository.save(user);
    }

    @Public
    public void enable(String uuid) {
        MyUser user = getByUuid(uuid);
        user.setIsEnabled(true);
        repository.save(user);
    }

    @Public
    public Optional<MyUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Public
    public void archive(String uuid) {
        MyUser user = getByUuid(uuid);
        archive(user);
    }

    @Public
    public void archiveCurrent() {
        MyUser user = getCurrentUser();
        archive(user);
    }

    @Public
    public MyUser getCurrentUser() {
        return getAuthentication()
                .orElseThrow(() -> new IllegalStateException("Cannot get user principal"));
    }

    @Public
    public boolean isAuthenticated() {
        return getAuthentication().isPresent();
    }

    @Public
    public UserProfileDto getCurrentProfile() {
        MyUser currentUser = getCurrentUser();
        return mapper.toDto(currentUser);
    }

    private static Optional<MyUser> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(MyUser.class::isInstance)
                .map(MyUser.class::cast);
    }

    private void archive(MyUser user) {
        user.setIsEnabled(false);
        repository.save(user);
    }

    private MyUser getByUuid(String uuid) {
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("No user found by uuid: {}", uuid));
    }

    private void validateMyUserRequest(MyUserRequest request) {
        if (findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists. Please, login or use another email to sign up");
        }

        boolean isValidEmail = EmailValidationUtil.isValidEmail(request.getEmail());
        if (!isValidEmail) {
            throw new BadRequestException(String.format("Invalid email: %1$s", request.getEmail()));
        }
    }
}
