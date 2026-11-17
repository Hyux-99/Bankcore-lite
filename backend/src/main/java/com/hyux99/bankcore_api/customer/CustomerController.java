
package com.hyux99.bankcore_api.customer;

import com.hyux99.bankcore_api.customer.dto.CustomerRequest;
import com.hyux99.bankcore_api.customer.dto.CustomerResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Crear un cliente
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request) {

        CustomerResponse customer = customerService.createCustomer(request);

        return ResponseEntity.status(201).body(customer);
    }

    // Obtener todos los clientes
    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable Long id) {

        CustomerResponse customer = customerService.getCustomerById(id);

        return ResponseEntity.ok(customer);
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {

        CustomerResponse updatedCustomer =
                customerService.updateCustomer(id, request);

        return ResponseEntity.ok(updatedCustomer);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}



