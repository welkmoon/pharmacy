package kubg.edu.ua.automation.pharmacy.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthenticationRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
