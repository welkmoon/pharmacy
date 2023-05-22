package kubg.edu.ua.automation.pharmacy.mapper;

import kubg.edu.ua.automation.pharmacy.authentication.dto.MyUserRequest;
import kubg.edu.ua.automation.pharmacy.dto.UserProfileDto;
import kubg.edu.ua.automation.pharmacy.entity.MyUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public abstract class MyUserMapper {

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "password", ignore = true)
    public abstract MyUser toEntity(MyUserRequest request);

    public abstract UserProfileDto toDto(MyUser currentUser);

    @AfterMapping
    protected void afterToEntity(@MappingTarget MyUser user, MyUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
    }

}
