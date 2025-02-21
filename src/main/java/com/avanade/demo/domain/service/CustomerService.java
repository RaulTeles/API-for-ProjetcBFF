package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.port.input.GetCustomerUseCase;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService implements GetCustomerUseCase {

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
}
