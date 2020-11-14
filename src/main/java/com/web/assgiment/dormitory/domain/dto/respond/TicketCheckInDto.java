package com.web.assgiment.dormitory.domain.dto.respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCheckInDto {
    private Integer studentId;
    private Date checkIn;
}
