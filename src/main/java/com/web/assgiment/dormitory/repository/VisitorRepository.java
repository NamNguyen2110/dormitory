package com.web.assgiment.dormitory.repository;

import com.web.assgiment.dormitory.domain.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
}
