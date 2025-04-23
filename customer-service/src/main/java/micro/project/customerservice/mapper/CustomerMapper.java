package micro.project.customerservice.mapper;

import micro.project.customerservice.dto.CustomerDTO;
import micro.project.customerservice.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// "spring" permet l'injection automatique via @Autowired
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);

    Customer toEntity(CustomerDTO customerDto);
}