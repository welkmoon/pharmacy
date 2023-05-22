package kubg.edu.ua.automation.pharmacy.repository;

import kubg.edu.ua.automation.pharmacy.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByUuid(String uuid);

    Page<Product> getProductBy(Pageable pageable);

}
