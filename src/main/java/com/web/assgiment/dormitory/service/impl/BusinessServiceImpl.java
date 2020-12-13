package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.common.utils.CommonUtils;
import com.web.assgiment.dormitory.common.validator.group.RegexContant;
import com.web.assgiment.dormitory.domain.dto.request.UsedDto;
import com.web.assgiment.dormitory.domain.entity.Business;
import com.web.assgiment.dormitory.domain.dto.BusinessDto;
import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.request.BusinessRespondDto;
import com.web.assgiment.dormitory.domain.entity.Student;
import com.web.assgiment.dormitory.domain.entity.Used;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.mapper.ObjectMapperUtils;
import com.web.assgiment.dormitory.repository.BussinessRepository;
import com.web.assgiment.dormitory.repository.StudentRepository;
import com.web.assgiment.dormitory.repository.UsedRepository;
import com.web.assgiment.dormitory.service.BusinessService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service("businessService")
public class BusinessServiceImpl implements BusinessService {
    private final Map<String, Object> resultPage = new HashMap<>();
    @Autowired
    private BussinessRepository bussinessRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UsedRepository usedRepository;

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public BusinessDto saveService(BusinessRespondDto dto) throws UserValidateException {
        checkNullOrEmpty(dto);
        checkExist(dto);
        Business business = checkPattern(dto);
        business.setStatus(1);
        bussinessRepository.save(business);
        BusinessDto newBusiness = ObjectMapperUtils.toDto(business, BusinessDto.class);
        return newBusiness;

    }

    @Override
    public Map<String, Object> getAllService(PageDto pageDto) {
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getLimit(), Sort.by("serviceCode"));
        Page<Business> businessPage = bussinessRepository.getAllService(pageable);
        customizePagination(businessPage);
        return resultPage;
    }

    @Override
    public List<BusinessDto> deleteService(List<Integer> ids) throws UserValidateException {
        List<Business> businessList = new ArrayList<>();
        for (Integer id : ids) {
            Optional<Business> optional = bussinessRepository.findById(id);
            if (optional.isEmpty()) {
                throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
            }
            if (optional.get().getStatus() == 0) {
                throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target.not.exist"));
            }
            optional.get().setStatus(0);
            bussinessRepository.save(optional.get());
            businessList.add(optional.get());
        }
        List<BusinessDto> dtos = ObjectMapperUtils.toDto(businessList, BusinessDto.class);
        return dtos;
    }

    @Override
    public BusinessDto updateService(BusinessDto businessDto) throws UserValidateException {
        Optional<Business> optional = bussinessRepository.findById(businessDto.getServiceId());
        if (optional.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        if (optional.get().getStatus() == 0) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target.not.exist"));
        }
        checkPattern(businessDto);
        checkNullOrEmpty(businessDto);
        Business business = ObjectMapperUtils.toEntity(businessDto, Business.class);
        optional.get().setServiceCode(businessDto.getServiceCode());
        optional.get().setServiceName(businessDto.getServiceName());
        optional.get().setAmount(business.getAmount());
        optional.get().setStatus(business.getStatus());
        bussinessRepository.save(optional.get());
        BusinessDto dto = ObjectMapperUtils.toDto(optional.get(), BusinessDto.class);
        return dto;
    }

    @Override
    public Map<String, Object> filterByName(PageDto pageDto, String serviceName) {
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getLimit(), Sort.by("serviceCode"));
        Page<Business> businessPage = bussinessRepository.filterByName(pageable, serviceName);
        customizePagination(businessPage);
        return resultPage;
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void registerService(UsedDto usedDto) throws UserValidateException {
        Optional<Student> optionalStudent = studentRepository.findById(usedDto.getStudentId());
        Optional<Business> optionalBusiness = bussinessRepository.findById(usedDto.getServiceId());
        if (optionalStudent.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        if (optionalBusiness.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        Used used = new Used();
        used.setStudent(optionalStudent.get());
        used.setBusiness(optionalBusiness.get());
        used.setStartUsed(new Date());
        used.setStatus(1);
        usedRepository.save(used);
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void checkOutService(Integer usedServiceId) throws UserValidateException {
        Optional<Used> optionalUsed = usedRepository.findById(usedServiceId);
        if (optionalUsed.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        optionalUsed.get().setEndUsed(new Date());
        optionalUsed.get().setStatus(0);
        usedRepository.save(optionalUsed.get());
    }

    private void checkExist(BusinessRespondDto dto) throws UserValidateException {
        if (bussinessRepository.existsByServiceCode(dto.getServiceCode())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.service.code"));
        }
        if (bussinessRepository.existsByServiceName(dto.getServiceName())) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.service.name"));
        }
    }

    private void checkNullOrEmpty(BusinessRespondDto dto) throws UserValidateException {
        if (CommonUtils.isNullOrEmpty(dto.getServiceName()) ||
                CommonUtils.isNullOrEmpty(dto.getServiceCode()) || CommonUtils.isNullOrEmpty(Double.toString(dto.getAmount()))) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.null"));
        }
    }

    private Business checkPattern(BusinessRespondDto dto) throws UserValidateException {
        if (!dto.getServiceCode().matches(RegexContant.SERVICE_CODE_REGEX)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.service.code.pattern"));
        }
        Business business = ObjectMapperUtils.toEntity(dto, Business.class);
        return business;
    }

    private void customizePagination(Page<Business> page) {
        List<Business> businessList = page.getContent();
        List<BusinessDto> dtos = ObjectMapperUtils.toDto(businessList, BusinessDto.class);
        resultPage.put("data", dtos);
        resultPage.put("totalItems", page.getSize());
        resultPage.put("currentPage", page.getNumber());
        resultPage.put("currentItem", page.getTotalElements());
        resultPage.put("totalPages", page.getTotalPages());
    }
}
