package com.web.assgiment.dormitory.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVisitorDto implements Serializable {
    private Integer studentId;
    private String cardId;
    private String visitorName;
    private String dateOfBirth;
}
