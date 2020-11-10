package com.web.assgiment.dormitory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Integer id;
    private String roomCode;
    private String roomType;
    private double amount;
    private Integer quantity;
    private Integer status;


}
