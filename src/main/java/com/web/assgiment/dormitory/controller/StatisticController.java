package com.web.assgiment.dormitory.controller;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/api/room")
public class StatisticController {
    @Autowired
    private BillService billService;

    @PostMapping("/bill-all")
    public ResponseEntity<ResponseData> getBill(@RequestParam("studentId") Integer studentId) {
        billService.processBill(studentId);
        return ResponseEntity.ok(ResponseData.ofSuccess("oki"));
    }
}
