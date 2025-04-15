package micro.project.billingservice.repository;

import micro.project.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}