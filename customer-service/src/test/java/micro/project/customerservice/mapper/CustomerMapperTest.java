package micro.project.customerservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import micro.project.customerservice.dto.CustomerDTO;
import micro.project.customerservice.entities.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void testToDto() {
        // Prepare the entity object
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        // Map entity to DTO
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // Assertions
        assertNotNull(customerDTO);
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getName(), customerDTO.getName());
    }

    @Test
    void testToEntity() {
        // Prepare the DTO object
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setName("John Doe");

        // Map DTO to entity
        Customer customer = customerMapper.toEntity(customerDTO);

        // Assertions
        assertNotNull(customer);
        assertEquals(customerDTO.getId(), customer.getId());
        assertEquals(customerDTO.getName(), customer.getName());
    }
}
