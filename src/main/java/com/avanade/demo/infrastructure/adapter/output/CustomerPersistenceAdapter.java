package com.avanade.demo.infrastructure.adapter.output;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import com.avanade.demo.domain.model.Customer;
import com.avanade.demo.domain.model.CustomerContact;
import com.avanade.demo.domain.model.CustomerDocument;
import com.avanade.demo.infrastructure.adapter.output.repository.CustomerContactRepository;
import com.avanade.demo.infrastructure.adapter.output.repository.CustomerDocumentRepository;
import com.avanade.demo.infrastructure.adapter.output.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerPersistenceAdapter implements CustomerOutput {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDocumentRepository customerDocumentRepository;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    private CustomerDTO mapCustomerToDTO(Customer customer) {
        Long customerId = customer.getId();

        List<CustomerDocument> documents = customerDocumentRepository.findByCustomerId(customerId);
        List<CustomerDocumentDTO> documentDTOs = documents.stream()
                .map(doc -> new CustomerDocumentDTO(doc.getDocument(), doc.getDocumentType().getName()))
                .toList();

        List<CustomerContact> contacts = customerContactRepository.findByCustomerId(customerId);
        List<CustomerContactDTO> contactDTOs = contacts.stream().map(contact ->
                new CustomerContactDTO(contact.getContactValue(), contact.getCustomerContactType().getName())).toList();

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getSegment().getName(),
                documentDTOs,
                contactDTOs
        );
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        final Customer cust = customerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));
        return mapCustomerToDTO(cust);
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        final Customer cust = customerRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Cliente não encontrado"));
        return mapCustomerToDTO(cust);
    }

    @Override
    public CustomerDTO getCustomerByDocumentNumber(String documentNumber) {
        CustomerDocument document = customerDocumentRepository.findByDocument(documentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Documento não encontrado"));

        Customer cust = document.getCustomer();

        return mapCustomerToDTO(cust);
    }
}
