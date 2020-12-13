package com.web.assgiment.dormitory.controller;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.TicketDto;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.service.TicketService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "v1/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/check-in")
    public ResponseEntity<ResponseData> checkInTicketDaily(@RequestParam("studentId") Integer studentId) throws UserValidateException {
        ticketService.saveTicketCheckIn(studentId);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.create")));
    }

    @PutMapping("/check-out")
    public ResponseEntity<ResponseData> checkOutTicketDaily(@RequestParam("studentId") Integer studentId) throws UserValidateException {
        ticketService.saveTicketCheckOut(studentId);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.update")));
    }

    @PutMapping("/delete-ticket")
    public ResponseEntity<ResponseData> deleteTicket(@RequestBody List<Integer> ids) throws UserValidateException {
        List<TicketDto> dtos = ticketService.deleteTicket(ids);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.delete"), dtos));
    }

    @PostMapping("/register-ticket-monthly")
    public ResponseEntity<ResponseData> registerTicketMonthly(@RequestParam("studentId") Integer studentId) throws UserValidateException {
        ticketService.registerTicketMonthly(studentId);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.create")));
    }
    @GetMapping("")
    public ResponseEntity<ResponseData> getAllTickets(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit) throws UserValidateException {
        PageDto pageDto = new PageDto(offset, limit);
        Map<String, Object> map = ticketService.getAllTicket(pageDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.get"), map));
    }

}
