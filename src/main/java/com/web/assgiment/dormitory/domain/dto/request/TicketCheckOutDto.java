package com.web.assgiment.dormitory.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCheckOutDto {
    private Integer id;
    private double charges;
    private Date checkOut;
    private Integer status;
}
