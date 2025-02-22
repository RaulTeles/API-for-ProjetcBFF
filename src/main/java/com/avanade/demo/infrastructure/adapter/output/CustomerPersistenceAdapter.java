package com.avanade.demo.infrastructure.adapter.output;

import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import com.avanade.demo.domain.model.Customer;
import com.avanade.demo.domain.model.CustomerDocument;
import com.avanade.demo.infrastructure.adapter.output.repository.CustomerDocumentRepository;
import com.avanade.demo.infrastructure.adapter.output.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CustomerPersistenceAdapter implements CustomerOutput {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDocumentRepository customerDocumentRepository;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        final Customer cust = customerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));

        List<CustomerDocument> documents = customerDocumentRepository.findByCustomerId(id);

        List<CustomerDocumentDTO> documentDTOs = documents.stream().map(doc ->
                new CustomerDocumentDTO(doc.getDocument(), doc.getDocumentType().getName())).toList();

        return new CustomerDTO(cust.getId(), cust.getName(), cust.getSegment().getName(),
                documentDTOs, Collections.emptyList());
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        final Customer cust = customerRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));

        return new CustomerDTO(cust.getId(), cust.getName(), cust.getSegment().getName(),
                Collections.emptyList(), Collections.emptyList());
    }
}
