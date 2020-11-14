package com.web.assgiment.dormitory.domain.dto.respond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRespondDto {
    private String roomCode;
    private String roomType;
    private double amount;
    private Integer quantity;
}
