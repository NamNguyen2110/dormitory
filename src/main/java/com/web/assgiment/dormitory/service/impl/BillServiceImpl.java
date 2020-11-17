package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.common.validator.group.RegexContant;
import com.web.assgiment.dormitory.domain.dto.BillDto;
import com.web.assgiment.dormitory.domain.dto.request.BillExportDto;
import com.web.assgiment.dormitory.domain.dto.request.BillServiceDto;
import com.web.assgiment.dormitory.domain.dto.request.BillServiceRequestDto;
import com.web.assgiment.dormitory.domain.entity.Bill;
import com.web.assgiment.dormitory.domain.entity.Student;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.repository.BillRepository;
import com.web.assgiment.dormitory.repository.BillRepositoryCustom;
import com.web.assgiment.dormitory.repository.StudentRepository;
import com.web.assgiment.dormitory.service.BillService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("billService")
public class BillServiceImpl implements BillService {
    private final Map<String, Object> resultPage = new HashMap<>();
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillRepositoryCustom billRepositoryCustom;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<BillDto> getAllBill() {
        return billRepositoryCustom.getAllBill();
    }

    @Override
    public void processBill(BillExportDto dto) throws UserValidateException {
        Optional<Student> optional = studentRepository.findById(dto.getStudentId());
        if (optional.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        if (!dto.getStartDay().matches(RegexContant.DATETIME) || !dto.getEndDay().matches(RegexContant.DATETIME)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.dateOfBirth.pattern"));
        }
        Bill bill = new Bill();
        double roomCharge = billRepositoryCustom.roomCharge(dto.getStudentId());
        bill.setTotalRoom(roomCharge);
        double serviceCharge = billRepositoryCustom.serviceChargeByDate(dto);
        bill.setTotalService(serviceCharge);
        bill.setTotalBill(roomCharge + serviceCharge);
        bill.setStudent(optional.get());
        bill.setExportDate(new Date());
        billRepository.save(bill);
    }

    @Override
    public List<BillServiceDto> getAllService(BillServiceRequestDto dto) throws UserValidateException {
        if (!dto.getStartDate().matches(RegexContant.DATETIME)
                || !dto.getEndDate().matches(RegexContant.DATETIME)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.datetime.pattern"));
        }
        return billRepositoryCustom.getAllService(dto);
    }

    private void customizePagination(Page<Bill> page) {
        List<Bill> businessList = page.getContent();
        resultPage.put("data", businessList);
        resultPage.put("totalItems", page.getSize());
        resultPage.put("currentPage", page.getNumber());
        resultPage.put("currentItem", page.getTotalElements());
        resultPage.put("totalPages", page.getTotalPages());
    }
}
