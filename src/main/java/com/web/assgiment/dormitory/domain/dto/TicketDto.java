package com.web.assgiment.dormitory.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.assgiment.dormitory.domain.dto.request.TicketCheckInDto;
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
