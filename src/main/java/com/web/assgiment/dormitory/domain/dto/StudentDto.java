package com.web.assgiment.dormitory.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.assgiment.dormitory.domain.dto.respond.StudentRespondDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto extends StudentRespondDto {
    private Integer studentId;
    @JsonIgnore
    private Integer status;
}
