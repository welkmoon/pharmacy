package kubg.edu.ua.automation.pharmacy.mapper;

import kubg.edu.ua.automation.pharmacy.dto.ProductDto;
import kubg.edu.ua.automation.pharmacy.dto.ProductRequest;
import kubg.edu.ua.automation.pharmacy.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductRequest request);
}
