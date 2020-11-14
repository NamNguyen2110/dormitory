package com.web.assgiment.dormitory.domain.dto;

import com.web.assgiment.dormitory.domain.dto.respond.TicketCheckInDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto extends TicketCheckInDto {
    private Integer ticketId;
    private Integer status;
}
