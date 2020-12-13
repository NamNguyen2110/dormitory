package com.web.assgiment.dormitory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDto implements Serializable {
    private Integer id;
}
