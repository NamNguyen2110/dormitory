package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.domain.entity.Bill;
import com.web.assgiment.dormitory.repository.BillRepository;
import com.web.assgiment.dormitory.repository.BillRepositoryCustom;
import com.web.assgiment.dormitory.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("billService")
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillRepositoryCustom billRepositoryCustom;

    @Override
    public void processBill(Integer studentId) {
        Bill bill = new Bill();
        bill.setTotalRoom(billRepositoryCustom.roomCharge(studentId));
        billRepository.save(bill);
    }
}
