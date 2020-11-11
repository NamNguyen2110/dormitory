package com.web.assgiment.dormitory.service;

import com.web.assgiment.dormitory.dto.PageDto;
import com.web.assgiment.dormitory.dto.RoomDto;
import com.web.assgiment.dormitory.dto.respond.RoomRespondDto;
import com.web.assgiment.dormitory.exception.BadRequestException;
import com.web.assgiment.dormitory.exception.UserValidateException;

import java.util.*;

public interface RoomService {
    RoomDto saveRoom(RoomRespondDto roomDto) throws UserValidateException, BadRequestException;

    Map<String, Object> getAllRoom(PageDto pageDto) throws UserValidateException;

    List<RoomDto> deleteRoom(List<Integer> id) throws UserValidateException;

    RoomDto updateOneRoom(RoomDto roomDto) throws UserValidateException, BadRequestException;

    Map<String, Object> filterByRoomCode(PageDto pageDto, String roomCode);
}
