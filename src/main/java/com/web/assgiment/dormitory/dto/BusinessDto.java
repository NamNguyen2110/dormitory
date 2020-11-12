package com.web.assgiment.dormitory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.assgiment.dormitory.dto.respond.BusinessRespondDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto extends BusinessRespondDto {
    private Integer id;
    @JsonIgnore
    private Integer status;
}
