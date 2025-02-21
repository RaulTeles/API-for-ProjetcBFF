package com.avanade.demo.application.port.input;

import com.avanade.demo.application.dto.CustomerDTO;

public interface GetCustomerUseCase {

    CustomerDTO getCustomerById(Long id);

    CustomerDTO getCustomerByName(String username);
}