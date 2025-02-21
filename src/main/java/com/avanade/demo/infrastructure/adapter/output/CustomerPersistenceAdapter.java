package com.avanade.demo.infrastructure.adapter.output;

import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import com.avanade.demo.domain.model.Customer;
import com.avanade.demo.infrastructure.adapter.output.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomerPersistenceAdapter implements CustomerOutput {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        final Customer cust = customerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));

        return new CustomerDTO(cust.getId(), cust.getName(), cust.getSegment().getName(),
                Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        final Customer cust = customerRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));

        return new CustomerDTO(cust.getId(), cust.getName(), cust.getSegment().getName(),
                Collections.emptyList(), Collections.emptyList());
    }
}
