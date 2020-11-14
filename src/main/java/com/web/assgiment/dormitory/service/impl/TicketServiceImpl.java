package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.TicketDto;
import com.web.assgiment.dormitory.domain.dto.respond.StudentRespondDto;
import com.web.assgiment.dormitory.domain.entity.Student;
import com.web.assgiment.dormitory.domain.entity.Ticket;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.mapper.ObjectMapperUtils;
import com.web.assgiment.dormitory.repository.StudentRepository;
import com.web.assgiment.dormitory.repository.TicketRepository;
import com.web.assgiment.dormitory.service.TicketService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {
    private final Map<String, Object> resultPage = new HashMap<>();
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void saveTicketCheckIn(Integer studentId) throws UserValidateException {
        Optional<Student> optional = studentRepository.findById(studentId);
        if (optional.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        Ticket ticket = new Ticket();
        Date currentDate = new Date();
        ticket.setCheckIn(currentDate);
        ticket.setStudent(optional.get());
        ticket.setStatus(1);
        ticketRepository.save(ticket);
    }

    @Override
    public void saveTicketCheckOut(Integer studentId) throws UserValidateException {
        Optional<Ticket> optionalTicket = ticketRepository.findById(studentId);
        if (optionalTicket.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        optionalTicket.get().setCharges(3000);
        optionalTicket.get().setStatus(0);
        optionalTicket.get().setCheckOut(new Date());
        ticketRepository.save(optionalTicket.get());
    }

    @Override
    public List<TicketDto> deleteTicket(List<Integer> ids) throws UserValidateException {
        List<Ticket> tickets = new ArrayList<>();
        for (Integer ticketId : ids) {
            Optional<Ticket> optional = ticketRepository.findById(ticketId);
            if (optional.isEmpty()) {
                throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
            }
            if (optional.get().getStatus() == 0) {
                throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target.not.exist"));
            }
            optional.get().setStatus(0);
            ticketRepository.save(optional.get());
            tickets.add(optional.get());
        }
        List<TicketDto> dto = ObjectMapperUtils.toDto(tickets, TicketDto.class);
        return dto;
    }

    @Override
    public void registerTicketMonthly(Integer studentId) throws UserValidateException {
        Optional<Student> optional = studentRepository.findById(studentId);
        if (optional.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        Date currentDate = new Date();
        Ticket ticket = new Ticket();
        ticket.setCheckIn(currentDate);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date expriedDate = calendar.getTime();
        ticket.setCheckOut(expriedDate);
        ticket.setCharges(100000);
        ticket.setStatus(1);
        ticket.setStudent(optional.get());
        ticketRepository.save(ticket);
    }

    @Override
    public Map<String, Object> getAllTicket(PageDto pageDto) throws UserValidateException {
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getLimit());
        Page<Ticket> ticketPage = ticketRepository.getAllTicket(pageable);
        customizePagination(ticketPage);
        return resultPage;
    }

    private void customizePagination(Page<Ticket> page) {
        List<Ticket> roomList = page.getContent();
        resultPage.put("data", roomList);
        resultPage.put("totalItems", page.getSize());
        resultPage.put("currentPage", page.getNumber());
        resultPage.put("currentItem", page.getTotalElements());
        resultPage.put("totalPages", page.getTotalPages());
    }
}
