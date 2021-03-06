package com.web.assgiment.dormitory.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillServiceRequestDto {
    private String startDate;
    private String endDate;
}
