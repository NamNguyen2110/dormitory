package com.web.assgiment.dormitory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto1 implements Serializable {
    private Integer id;
    private Date checkIn;
    private Date checkOut;
    private double charge;
    private Integer status;
}
