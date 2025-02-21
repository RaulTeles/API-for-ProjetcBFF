package com.avanade.demo.infrastructure.adapter.output.repository;

import com.avanade.demo.domain.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Long> {
}