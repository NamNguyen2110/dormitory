package com.web.assgiment.dormitory.controller;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.domain.dto.PageDto;
import com.web.assgiment.dormitory.domain.dto.RoomDto;
import com.web.assgiment.dormitory.domain.dto.request.RoomRespondDto;
import com.web.assgiment.dormitory.exception.BadRequestException;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.service.RoomService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "v1/api/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public ResponseEntity<ResponseData> getAllRooms(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit) throws UserValidateException {
        PageDto pageDto = new PageDto(offset, limit);
        Map<String, Object> map = roomService.getAllRoom(pageDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.get"), map));
    }

    @PostMapping("/create-room")
    public ResponseEntity<ResponseData> createOneRoom(@RequestBody RoomRespondDto roomDto) throws UserValidateException, BadRequestException {
        RoomDto newRoomDto = roomService.saveRoom(roomDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.create"), newRoomDto));
    }

    @PutMapping("/delete-room")
    public ResponseEntity<ResponseData> deleteRoom(@RequestBody List<Integer> id) throws UserValidateException {
        List<RoomDto> dtoList = roomService.deleteRoom(id);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.delete"), dtoList));
    }

    @PutMapping("/update-room")
    public ResponseEntity<ResponseData> updateRoom(@RequestBody RoomDto roomDto) throws UserValidateException, BadRequestException {
        RoomDto updateRoom = roomService.updateOneRoom(roomDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.update"), updateRoom));
    }

    @GetMapping("/search-by-roomCode")
    public ResponseEntity<ResponseData> searchByName(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                     @RequestParam("q") String roomCode) {
        PageDto pageDto = new PageDto(offset, limit);
        Map<String, Object> map = roomService.filterByRoomCode(pageDto, roomCode);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("dormitory.message.system.search"), map));
    }

}
