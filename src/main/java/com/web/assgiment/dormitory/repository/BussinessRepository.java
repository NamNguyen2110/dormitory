package com.web.assgiment.dormitory.repository;

import com.web.assgiment.dormitory.domain.entity.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BussinessRepository extends JpaRepository<Business, Integer> {
    @Query("SELECT DISTINCT b FROM Business b WHERE b.serviceName LIKE CONCAT('%',:serviceName,'%')")
    Page<Business> filterByName(Pageable pageable, @Param("serviceName") String serviceName);

    @Query("SELECT DISTINCT b FROM Business b")
    Page<Business> getAllService(Pageable pageable);

    boolean existsByServiceName(String serviceName);

    boolean existsByServiceCode(String serviceCode);
}
