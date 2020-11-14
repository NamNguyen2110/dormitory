package com.web.assgiment.dormitory.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.assgiment.dormitory.domain.dto.respond.BusinessRespondDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto extends BusinessRespondDto {
    private Integer serviceId;
    @JsonIgnore
    private Integer status;
}
