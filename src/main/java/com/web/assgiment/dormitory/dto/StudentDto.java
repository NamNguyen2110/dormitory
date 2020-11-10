package com.web.assgiment.dormitory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.assgiment.dormitory.dto.respond.StudentRespondDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto extends StudentRespondDto {
    private Integer roomId;
    @JsonIgnore
    private Integer status;
}
