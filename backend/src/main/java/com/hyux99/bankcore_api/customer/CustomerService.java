
package com.hyux99.bankcore_api.customer;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    
    public Optional<Customer> updateCustomer(Long id, Customer customerDetails) {

        Optional<Customer> existingCustomer = customerRepository.findById(id);

        if (existingCustomer.isEmpty()) {
            return Optional.empty();
        }

        Customer customer = existingCustomer.get();

        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setDocumentNumber(customerDetails.getDocumentNumber());
        customer.setEmail(customerDetails.getEmail());

        return Optional.of(customerRepository.save(customer));
    }

    
    public boolean deleteCustomer(Long id) {

        if (!customerRepository.existsById(id)) {
            return false;
        }

        customerRepository.deleteById(id);

        return true;
    }
}
