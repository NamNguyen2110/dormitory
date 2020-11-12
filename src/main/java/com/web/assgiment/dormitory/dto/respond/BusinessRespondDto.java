package com.web.assgiment.dormitory.dto.respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessRespondDto {
    private String serviceCode;
    private String serviceName;
    private double amount;
}
