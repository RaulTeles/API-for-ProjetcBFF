package com.avanade.demo.application.port.input;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerDTO;

public interface CustomerUseCase {

    CustomerDTO getCustomerById(Long id);

    CustomerDTO getCustomerByName(String username);

    CustomerDTO getCustomerByDocumentNumber(String documentNumber);

    CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO);

}