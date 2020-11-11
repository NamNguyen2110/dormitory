package com.web.assgiment.dormitory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.assgiment.dormitory.dto.respond.RoomRespondDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto extends RoomRespondDto {
    private Integer id;
    @JsonIgnore
    private Integer status;
}
