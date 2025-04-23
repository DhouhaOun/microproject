package micro.project.customerservice.service;

import micro.project.customerservice.dto.CustomerDTO;


import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomer(Long id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long id);

}
