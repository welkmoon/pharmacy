package kubg.edu.ua.automation.pharmacy.repository;

import kubg.edu.ua.automation.pharmacy.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByEmail(String email);

    Optional<MyUser> findByUuid(String uuid);

}
