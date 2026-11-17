package com.hyux99.bankcore_api.customer.dto;

public class CustomerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String email;

    public CustomerResponse() {
    }

    public CustomerResponse(
            Long id,
            String firstName,
            String lastName,
            String documentNumber,
            String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }
}