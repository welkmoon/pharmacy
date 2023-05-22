package kubg.edu.ua.automation.pharmacy.controller;

import kubg.edu.ua.automation.pharmacy.authentication.dto.MyUserRequest;
import kubg.edu.ua.automation.pharmacy.dto.UserProfileDto;
import kubg.edu.ua.automation.pharmacy.service.MyUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MyUserController {

    private final MyUserService userService;

    @PostMapping
    public void create(@RequestBody @Valid MyUserRequest request) {
        log.info("Creating user with email: {}", request.getEmail());
        userService.create(request);
    }

    @PatchMapping
    public void enable(@RequestParam String uuid) {
        log.info("Enabling user with uuid: {}", uuid);
        userService.enable(uuid);
    }

    @PatchMapping("/archive")
    @PreAuthorize("isAuthenticated()")
    public void archiveCurrent() {
        log.info("Archiving current user");
        userService.archiveCurrent();
    }

    @PatchMapping("/archive/{uuid}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void archive(@PathVariable String uuid) {
        log.info("Archiving user by uuid: {}", uuid);
        userService.archive(uuid);
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public UserProfileDto getCurrentUserProfile() {
        log.info("Getting current user profile");
        return userService.getCurrentProfile();
    }

}
