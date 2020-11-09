package com.web.assgiment.dormitory.common.validator.factory;

import com.web.assgiment.dormitory.common.utils.CommonUtils;
import com.web.assgiment.dormitory.common.validator.ValidateObject;
import com.web.assgiment.dormitory.common.validator.ValidatorMessage;
import com.web.assgiment.dormitory.common.validator.ValidatorService;
import com.web.assgiment.dormitory.common.validator.exception.FormatInvalidException;
import com.web.assgiment.dormitory.common.validator.group.MaxLengthGroup;
import com.web.assgiment.dormitory.common.validator.group.MessageField;
import com.web.assgiment.dormitory.common.validator.group.RequiredGroup;

public class RequiredService implements ValidatorService {

    @Override
    public ValidatorMessage process(ValidateObject object) throws FormatInvalidException {
        return required(object.getValue(), object.getFieldName(), (RequiredGroup) object.getValidatorGroup());
    }

    public ValidatorMessage required(Object value, String fieldName, RequiredGroup requiredGroup) throws FormatInvalidException {
        if (requiredGroup.isRequired() && isEmpty(value)) {
            return new ValidatorMessage(MessageField.required, fieldName);
        }
        return null;
    }

    private boolean isEmpty(Object value) {
        if (value == null)
            return true;
        if (value instanceof String)
            return CommonUtils.isNullOrEmpty((String) value);

        return false;
    }
}
