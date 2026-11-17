
package com.hyux99.bankcore_api.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByDocumentNumberAndIdNot(String documentNumber, Long id);
}

