package com.web.assgiment.dormitory.controller;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.domain.dto.BillDto;
import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.request.BillExportDto;
import com.web.assgiment.dormitory.domain.entity.Bill;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.service.BillService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "v1/api/bill")
public class StatisticController {
    @Autowired
    private BillService billService;

    @PostMapping("/process-bill")
    @ApiOperation("Pick student up and import to bill table in database not show data")
    public ResponseEntity<ResponseData> processOneBill(@RequestBody BillExportDto dto) throws UserValidateException {
        billService.processBill(dto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.create")));
    }

//    @GetMapping("")
//    public ResponseEntity<ResponseData> getAllBill(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
//                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit) throws UserValidateException {
//        PageDto pageDto = new PageDto(offset, limit);
//        Map<String, Object> map = billService.getAllBill(pageDto);
//        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.get"), map));
//    }

    @GetMapping("")
    public ResponseEntity<ResponseData> getAllBill() throws UserValidateException {
        List<BillDto> billList = billService.getAllBill();
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.get"), billList));
    }
}
