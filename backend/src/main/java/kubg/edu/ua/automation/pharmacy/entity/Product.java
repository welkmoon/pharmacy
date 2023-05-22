package kubg.edu.ua.automation.pharmacy.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product extends IdHolder {

    @NotEmpty
    private String name;

    @PositiveOrZero
    private BigDecimal price;

    private String imgUrl;

    private String description;

}
