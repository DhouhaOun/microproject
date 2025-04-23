package micro.project.customerservice.serviceImpl;

import micro.project.customerservice.dto.CustomerDTO;
import micro.project.customerservice.entities.Customer;
import micro.project.customerservice.mapper.CustomerMapper;
import micro.project.customerservice.repository.CustomerRepository;
import micro.project.customerservice.service.CustomerService;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        Customer customer= customerRepository.findById(id).orElse(null);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customerSaved = customerRepository.save(customerMapper.toEntity(customerDTO));
        return customerMapper.toDto(customerSaved);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        if (!customerRepository.existsById(customerDTO.getId())) {
            return null; // or throw an exception
        }
        Customer customerUpdated = customerRepository.save(customerMapper.toEntity(customerDTO));
        return customerMapper.toDto(customerUpdated);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);

    }
}
