package micro.project.billingservice.repository;

import micro.project.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository  extends JpaRepository<Bill, Long> {
}
