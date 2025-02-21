package com.avanade.demo.infrastructure.adapter.output.repository;

import com.avanade.demo.domain.model.CustomerContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactTypeRepository extends JpaRepository<CustomerContactType, Long> {
}