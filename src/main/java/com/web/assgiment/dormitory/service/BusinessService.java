package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.domain.dto.BusinessDto;
import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.request.BusinessRespondDto;
import com.web.assgiment.dormitory.domain.dto.request.UsedDto;
import com.web.assgiment.dormitory.exception.UserValidateException;

import java.util.*;

public interface BusinessService {
    BusinessDto saveService(BusinessRespondDto dto) throws UserValidateException;

    Map<String, Object> getAllService(PageDto pageDto);

    List<BusinessDto> deleteService(List<Integer> id) throws UserValidateException;

    BusinessDto updateService(BusinessDto businessDto) throws UserValidateException;

    Map<String, Object> filterByName(PageDto pageDto, String serviceName);

    void registerService(UsedDto usedDto) throws UserValidateException;

    void checkOutService(Integer usedServiceId) throws UserValidateException;
}
