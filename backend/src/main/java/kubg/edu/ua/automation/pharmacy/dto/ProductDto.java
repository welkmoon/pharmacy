package kubg.edu.ua.automation.pharmacy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private String uuid;

    private String name;

    private String description;

    private String imgUrl;

    private BigDecimal price;

    private Boolean isFavourite;

}
