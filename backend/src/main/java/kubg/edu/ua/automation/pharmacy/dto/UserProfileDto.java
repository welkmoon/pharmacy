package kubg.edu.ua.automation.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserProfileDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDay;

    private String role;

}
