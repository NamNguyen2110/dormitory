package com.web.assgiment.dormitory.repository;

import com.web.assgiment.dormitory.domain.dto.BillDto;
import com.web.assgiment.dormitory.domain.dto.request.BillExportDto;

import java.util.List;

public interface BillRepositoryCustom {
    double roomCharge(Integer studentId);

    double serviceChargeByDate(BillExportDto dto);

    List<BillDto> getAllBill();
}
