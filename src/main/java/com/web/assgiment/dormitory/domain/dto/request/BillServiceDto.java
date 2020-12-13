package com.web.assgiment.dormitory.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillServiceDto {
    private String studentCode;
    private String serviceCode;
    private String serviceName;
    private Date startUsed;
    private Date endUsed;
    private double total;
}
