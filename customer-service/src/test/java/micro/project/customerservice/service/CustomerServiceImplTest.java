package micro.project.customerservice.service;

import micro.project.customerservice.dto.CustomerDTO;
import micro.project.customerservice.entities.Customer;
import micro.project.customerservice.mapper.CustomerMapper;
import micro.project.customerservice.repository.CustomerRepository;
import micro.project.customerservice.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setName("John Doe");
    }

    @Test
    void testGetCustomers() {
        List<Customer> customers = List.of(customer);
        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        List<CustomerDTO> result = customerService.getCustomers();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(customerRepository).findAll();
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testGetCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomer(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(customerRepository).findById(1L);
        verify(customerMapper).toDto(customer);
    }

    @Test
    void testCreateCustomer() {
        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.createCustomer(customerDTO);

        assertEquals("John Doe", result.getName());
        verify(customerRepository).save(customer);
    }

    @Test
    void testUpdateCustomer_whenCustomerExists() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.updateCustomer(customerDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerRepository).existsById(1L);
        verify(customerRepository).save(customer);
    }

    @Test
    void testUpdateCustomer_whenCustomerDoesNotExist() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        CustomerDTO result = customerService.updateCustomer(customerDTO);

        assertNull(result);
        verify(customerRepository).existsById(1L);
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomer(1L);

        verify(customerRepository).deleteById(1L);
    }
}
