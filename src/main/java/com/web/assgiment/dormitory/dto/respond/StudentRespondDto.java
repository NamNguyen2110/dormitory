package com.web.assgiment.dormitory.dto.respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRespondDto {
    private Integer id;
    private String studentCode;
    private String cardId;
    private String dateOfBirth;
    private String grade;
    private String address;
}
