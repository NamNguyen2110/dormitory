package com.web.assgiment.dormitory.common.validator;


import com.assignment.shoppingpetbe.common.validator.exception.FormatInvalidException;

public interface ValidatorService {
    ValidatorMessage process(ValidateObject obj) throws FormatInvalidException;
}
