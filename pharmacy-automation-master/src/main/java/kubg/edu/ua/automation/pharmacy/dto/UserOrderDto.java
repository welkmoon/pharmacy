package kubg.edu.ua.automation.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class UserOrderDto {

    private Long id;

    private Long number;

    private String status;

    private Instant time;

}
