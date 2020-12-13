package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.domain.dto.request.RegisterVisitorDto;
import com.web.assgiment.dormitory.domain.dto.request.VisitorDto;
import com.web.assgiment.dormitory.exception.UserValidateException;

public interface VisitorService {
    VisitorDto registerVisit(RegisterVisitorDto dto) throws UserValidateException;
}
