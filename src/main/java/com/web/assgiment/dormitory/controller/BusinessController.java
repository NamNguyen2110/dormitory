package com.web.assgiment.dormitory.controller;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.dto.BusinessDto;
import com.web.assgiment.dormitory.dto.PageDto;
import com.web.assgiment.dormitory.dto.respond.BusinessRespondDto;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.service.BusinessService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "v1/api/service")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @PostMapping("/create-service")
    public ResponseEntity<ResponseData> createService(@RequestBody BusinessRespondDto dto) throws UserValidateException {
        BusinessDto businessDto = businessService.saveService(dto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.create"), businessDto));
    }

    @GetMapping("/")
    public ResponseEntity<ResponseData> getAllService(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        PageDto pageDto = new PageDto(offset, limit);
        Map<String, Object> map = businessService.getAllService(pageDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.get"), map));
    }

    @PutMapping("/delete-service")
    public ResponseEntity<ResponseData> deleteService(@RequestBody List<Integer> listId) throws UserValidateException {
        List<BusinessDto> dtos = businessService.deleteService(listId);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.delete"), dtos));
    }

    @PutMapping("/update-service")
    public ResponseEntity<ResponseData> updateService(@RequestBody BusinessDto businessDto) throws UserValidateException {
        BusinessDto dto = businessService.updateService(businessDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.update"), dto));
    }

    @GetMapping("/search-service-name")
    public ResponseEntity<ResponseData> searchByName(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                     @RequestParam("q") String serviceCode) {
        PageDto pageDto = new PageDto(offset, limit);
        Map<String, Object> map = businessService.filterByName(pageDto, serviceCode);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.search"), map));
    }
}
