package kubg.edu.ua.automation.pharmacy.authentication.controller;

import kubg.edu.ua.automation.pharmacy.authentication.dto.AuthenticationRequest;
import kubg.edu.ua.automation.pharmacy.authentication.service.AuthenticationService;
import kubg.edu.ua.automation.pharmacy.dto.StringValueDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public StringValueDto authenticate(@RequestBody @Valid AuthenticationRequest request) {
        log.info("Authentication");
        return authenticationService.authenticate(request);
    }

}
