package kubg.edu.ua.automation.pharmacy.repository;

import kubg.edu.ua.automation.pharmacy.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findByUserIdOrderByTimeDesc(Long userId);

}
