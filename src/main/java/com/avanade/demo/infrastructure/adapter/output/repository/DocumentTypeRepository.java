package com.avanade.demo.infrastructure.adapter.output.repository;

import com.avanade.demo.domain.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
}