package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.domain.dto.BillDto;
import com.web.assgiment.dormitory.domain.dto.request.BillExportDto;
import com.web.assgiment.dormitory.domain.dto.request.BillServiceDto;
import com.web.assgiment.dormitory.domain.dto.request.BillServiceRequestDto;
import com.web.assgiment.dormitory.exception.UserValidateException;

import java.util.List;

public interface BillService {
    List<BillDto> getAllBill();

    void processBill(BillExportDto dto) throws UserValidateException;

    List<BillServiceDto> getAllService(BillServiceRequestDto dto) throws UserValidateException;
}
