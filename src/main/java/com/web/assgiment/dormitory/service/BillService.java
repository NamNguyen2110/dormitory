package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.domain.dto.BillDto;
import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.request.BillExportDto;
import com.web.assgiment.dormitory.domain.entity.Bill;
import com.web.assgiment.dormitory.exception.UserValidateException;

import java.util.HashMap;
import java.util.*;

public interface BillService {
    List<BillDto> getAllBill();

    void processBill(BillExportDto dto) throws UserValidateException;
}
