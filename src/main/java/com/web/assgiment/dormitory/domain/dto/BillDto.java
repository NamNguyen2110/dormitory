package com.web.assgiment.dormitory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Integer id;
    private String studentCode;
    private double totalRoom;
    private double totalService;
    private double totalBill;
    private Date exportDate;
}
