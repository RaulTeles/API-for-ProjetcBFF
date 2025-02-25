package com.avanade.demo.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_documents")
public class CustomerDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String document;

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
