package com.avanade.demo.infrastructure.adapter.output.repository;

import com.avanade.demo.domain.model.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactRepository extends JpaRepository<CustomerContact, Long> {
}