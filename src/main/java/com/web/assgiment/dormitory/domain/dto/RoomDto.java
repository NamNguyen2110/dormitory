package com.web.assgiment.dormitory.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.assgiment.dormitory.domain.dto.respond.RoomRespondDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto extends RoomRespondDto {
    private Integer roomId;
    @JsonIgnore
    private Integer status;
}
