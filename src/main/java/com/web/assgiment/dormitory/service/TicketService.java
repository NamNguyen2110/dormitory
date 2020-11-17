package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.TicketDto;
import com.web.assgiment.dormitory.exception.UserValidateException;

import java.util.List;
import java.util.Map;

public interface TicketService {
    void saveTicketCheckIn(Integer studentId) throws UserValidateException;

    void saveTicketCheckOut(Integer studentId) throws UserValidateException;

    List<TicketDto> deleteTicket(List<Integer> id) throws UserValidateException;

    void registerTicketMonthly(Integer studentId) throws UserValidateException;

    Map<String, Object> getAllTicket(PageDto pageDto) throws UserValidateException;
}
