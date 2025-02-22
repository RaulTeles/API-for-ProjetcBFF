package com.avanade.demo.application.port.output;

import com.avanade.demo.application.dto.CustomerDTO;

public interface CustomerOutput {

    CustomerDTO getCustomerById(Long id);

    CustomerDTO getCustomerByName(String name);


}
