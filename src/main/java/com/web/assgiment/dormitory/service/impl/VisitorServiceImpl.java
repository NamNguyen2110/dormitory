package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.common.utils.CommonUtils;
import com.web.assgiment.dormitory.common.validator.group.RegexContant;
import com.web.assgiment.dormitory.domain.dto.request.RegisterVisitorDto;
import com.web.assgiment.dormitory.domain.dto.request.StudentRespondDto;
import com.web.assgiment.dormitory.domain.dto.request.VisitorDto;
import com.web.assgiment.dormitory.domain.entity.Visitor;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.mapper.ObjectMapperUtils;
import com.web.assgiment.dormitory.repository.VisitorRepository;
import com.web.assgiment.dormitory.service.VisitorService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public VisitorDto registerVisit(RegisterVisitorDto dto) throws UserValidateException {
        checkNullOrEmpty(dto);
        return null;
    }

    private void checkNullOrEmpty(RegisterVisitorDto dto) throws UserValidateException {
        if (CommonUtils.isNullOrEmpty(dto.getVisitorName()) ||
                CommonUtils.isNullOrEmpty(dto.getDateOfBirth()) ||
                CommonUtils.isNullOrEmpty(dto.getCardId())
        ) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.null"));
        }
    }

    private Visitor checkPattern(RegisterVisitorDto dto) throws UserValidateException {
        if (!dto.getDateOfBirth().matches(RegexContant.DATETIME)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.dateOfBirth.pattern"));
        }
        if (!dto.getCardId().matches(RegexContant.CARD_CODE_REGEX)) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.student.cardId.pattern"));
        }
        Visitor visitor = ObjectMapperUtils.toEntity(dto, Visitor.class);
        return visitor;
    }

}
