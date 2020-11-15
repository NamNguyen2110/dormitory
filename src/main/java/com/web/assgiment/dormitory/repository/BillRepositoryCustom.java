package com.web.assgiment.dormitory.repository;

public interface BillRepositoryCustom {
    double roomCharge(Integer studentId);

    double serviceCharge(Integer studentId);
}
