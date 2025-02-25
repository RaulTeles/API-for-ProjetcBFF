package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.port.input.CustomerUseCase;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService implements CustomerUseCase {

    @Autowired
    private CustomerOutput customerOutput;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerOutput.getCustomerById(id);
    }

    @Override
    public CustomerDTO getCustomerByName(String customerName) {
        return customerOutput.getCustomerByName(customerName);
    }

    @Override
    public CustomerDTO getCustomerByDocumentNumber(String documentNumber){
        return customerOutput.getCustomerByDocumentNumber(documentNumber);
    }

    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        return customerOutput.createCustomer(createCustomerDTO);
    }
}
