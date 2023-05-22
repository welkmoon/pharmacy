package kubg.edu.ua.automation.pharmacy.repository;

import kubg.edu.ua.automation.pharmacy.entity.MyUser;
import kubg.edu.ua.automation.pharmacy.entity.WishProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishProductRepository extends JpaRepository<WishProduct, Long> {

    boolean existsByUserAndProductUuid(MyUser user, String productUuid);

    Optional<WishProduct> findByUserAndProductUuid(MyUser user, String productUuid);

    Page<WishProduct> findByUser(MyUser user, Pageable pageable);

}
