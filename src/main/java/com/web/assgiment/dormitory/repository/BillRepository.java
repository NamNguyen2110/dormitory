package com.web.assgiment.dormitory.repository;

import com.web.assgiment.dormitory.domain.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query("SELECT b FROM Bill b")
    Page<Bill> getAllBill(Pageable pageable);
}
