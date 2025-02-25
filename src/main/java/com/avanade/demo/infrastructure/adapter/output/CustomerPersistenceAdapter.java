package com.avanade.demo.infrastructure.adapter.output;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import com.avanade.demo.domain.exception.ValidationException;
import com.avanade.demo.domain.model.*;
import com.avanade.demo.infrastructure.adapter.output.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerPersistenceAdapter implements CustomerOutput {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private CustomerDocumentRepository customerDocumentRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private CustomerContactTypeRepository customerContactTypeRepository;


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

    @Override
    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        // Valida se o CPF foi informado
        if (createCustomerDTO.documents().isEmpty()) {
            throw new ValidationException("O CPF é obrigatório.");
        }

        Segment segment = segmentRepository.findById(createCustomerDTO.segmentId())
                .orElseThrow(() -> new ValidationException("Segmento não encontrado."));

        Customer customer = new Customer();
        customer.setName(createCustomerDTO.name());
        customer.setSegment(segment);

        customer = customerRepository.save(customer);

        Customer finalCustomer = customer;

        List<CustomerDocumentDTO> documentDTOs = createCustomerDTO.documents().stream()
                .map(doc -> {
                    DocumentType documentType = documentTypeRepository.findByName(doc.documentType())
                            .orElseThrow(() -> new ValidationException("Tipo de documento não encontrado."));

                    CustomerDocument customerDocument = new CustomerDocument();
                    customerDocument.setCustomer(finalCustomer);
                    customerDocument.setDocumentType(documentType);
                    customerDocument.setDocument(doc.documentNumber());

                    customerDocumentRepository.save(customerDocument);

                    return new CustomerDocumentDTO(doc.documentNumber(), doc.documentType());
                })
                .collect(Collectors.toList());

        Customer finalCustomer1 = customer;
        List<CustomerContactDTO> contactDTOs = createCustomerDTO.contacts().stream()
                .map(contact -> {
                    CustomerContactType contactType = customerContactTypeRepository.findByName(contact.contactType())
                            .orElseThrow(() -> new ValidationException("Tipo de contato não encontrado."));

                    CustomerContact customerContact = new CustomerContact();
                    customerContact.setCustomer(finalCustomer1);
                    customerContact.setCustomerContactType(contactType);
                    customerContact.setContactValue(contact.contactCustomer());

                    customerContactRepository.save(customerContact);

                    return new CustomerContactDTO(contact.contactCustomer(), contact.contactType());
                })
                .collect(Collectors.toList());

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getSegment().getName(),
                documentDTOs,
                contactDTOs
        );
    }
}
