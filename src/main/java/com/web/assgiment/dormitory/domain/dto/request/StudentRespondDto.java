package com.web.assgiment.dormitory.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRespondDto {
    private Integer roomId;
    private String studentCode;
    private String cardId;
    private String dateOfBirth;
    private String grade;
    private String address;
}
