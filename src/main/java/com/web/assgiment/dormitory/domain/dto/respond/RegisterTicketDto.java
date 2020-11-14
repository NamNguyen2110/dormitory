package com.web.assgiment.dormitory.domain.dto.respond;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTicketDto {
    private Integer studentId;
    @JsonIgnore
    private double price;
    @JsonIgnore
    private Integer status;
}
