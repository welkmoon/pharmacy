package kubg.edu.ua.automation.pharmacy.mapper;

import kubg.edu.ua.automation.pharmacy.dto.UserOrderDto;
import kubg.edu.ua.automation.pharmacy.entity.UserOrder;
import org.mapstruct.Mapper;

@Mapper
public interface UserOrderMapper {

    UserOrderDto toDto(UserOrder order);

}
