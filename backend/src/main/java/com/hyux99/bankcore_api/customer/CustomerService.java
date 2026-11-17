
package com.hyux99.bankcore_api.customer;


import com.hyux99.bankcore_api.customer.dto.CustomerRequest;
import com.hyux99.bankcore_api.customer.dto.CustomerResponse;
import com.hyux99.bankcore_api.exception.DuplicateResourceException;
import com.hyux99.bankcore_api.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getDocumentNumber(),
                customer.getEmail()
        );
    }

    
    public CustomerResponse createCustomer(CustomerRequest request) {

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "A customer with this email already exists"
            );
        }

        if (customerRepository.existsByDocumentNumber(request.getDocumentNumber())) {
            throw new DuplicateResourceException(
                    "A customer with this document number already exists"
            );
        }

        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setDocumentNumber(request.getDocumentNumber());
        customer.setEmail(request.getEmail());

        Customer savedCustomer = customerRepository.save(customer);

        return toResponse(savedCustomer);
    }


    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + id
                ));

        return toResponse(customer);
    }

    
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + id
                ));

        if (customerRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new DuplicateResourceException(
                    "A customer with this email already exists"
            );
        }

        if (customerRepository.existsByDocumentNumberAndIdNot(
                request.getDocumentNumber(), id)) {

            throw new DuplicateResourceException(
                    "A customer with this document number already exists"
            );
        }

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setDocumentNumber(request.getDocumentNumber());
        customer.setEmail(request.getEmail());

        Customer updatedCustomer = customerRepository.save(customer);

        return toResponse(updatedCustomer);
    }


    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + id
                ));

        customerRepository.delete(customer);
    }
}


