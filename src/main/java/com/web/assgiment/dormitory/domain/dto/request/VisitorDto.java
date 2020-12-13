package com.web.assgiment.dormitory.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorDto extends RegisterVisitorDto {
    private Integer status;
}
