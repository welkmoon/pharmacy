package kubg.edu.ua.automation.pharmacy.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {

    @NotEmpty
    private String name;

    private String description;

    @PositiveOrZero
    private BigDecimal price;

}
