package com.avanade.demo.infrastructure.adapter.output.repository;

import com.avanade.demo.domain.model.CustomerContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerContactTypeRepository extends JpaRepository<CustomerContactType, Long> {
    Optional<CustomerContactType> findByName(String name);
}