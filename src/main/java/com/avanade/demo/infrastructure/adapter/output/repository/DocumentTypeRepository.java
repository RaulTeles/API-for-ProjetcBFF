package com.avanade.demo.infrastructure.adapter.output.repository;

import com.avanade.demo.domain.model.CustomerDocument;
import com.avanade.demo.domain.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    Optional<DocumentType> findByName(String documentNumber);

}