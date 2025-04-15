package micro.project.customerservice.repository;

import micro.project.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
