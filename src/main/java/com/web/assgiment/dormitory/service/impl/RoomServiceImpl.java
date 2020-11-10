package com.web.assgiment.dormitory.service.impl;

import com.web.assgiment.dormitory.common.utils.CommonUtils;
import com.web.assgiment.dormitory.common.validator.group.RegexContant;
import com.web.assgiment.dormitory.domain.Room;
import com.web.assgiment.dormitory.dto.PageDto;
import com.web.assgiment.dormitory.dto.RoomDto;
import com.web.assgiment.dormitory.exception.BadRequestException;
import com.web.assgiment.dormitory.exception.UserValidateException;
import com.web.assgiment.dormitory.mapper.ObjectMapperUtils;
import com.web.assgiment.dormitory.repository.RoomRepository;
import com.web.assgiment.dormitory.service.RoomService;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
    private final Map<String, Object> resultPage = new HashMap<>();
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDto saveRoom(RoomDto roomDto) throws UserValidateException, BadRequestException {
        if (CommonUtils.isNull(roomDto)) {
            throw new UserValidateException(MessageBundle.getMessage("common.validate.required"));
        }
        Room newRoom = validateRoomData(roomDto);
        newRoom.setStatus(1);
        roomRepository.save(newRoom);
        RoomDto newRoomDto = ObjectMapperUtils.toDto(newRoom, RoomDto.class);
        return newRoomDto;
    }

    @Override
    public Map<String, Object> getAllRoom(PageDto pageDto) throws UserValidateException {
        if (pageDto.getOffset() < 0 || pageDto.getLimit() < 0) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.target.object.page"));
        }
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getLimit(), Sort.by("roomCode"));
        Page<Room> roomPage = roomRepository.getAllRoom(pageable);
        customizePagination(roomPage);
        return resultPage;
    }

    @Override
    public List<RoomDto> deleteRoom(List<RoomDto> listIds) throws UserValidateException {
        List<Room> roomList = ObjectMapperUtils.toEntity(listIds, Room.class);
        List<RoomDto> dtos = new ArrayList<>();
        for (Room room : roomList) {
            Optional<Room> optional = roomRepository.findById(room.getId());
            if (optional.isEmpty()) {
                throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
            }
            if (optional.get().getStatus() == 0) {
                throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target.not.exist"));
            }
            optional.get().setStatus(0);
            roomRepository.save(optional.get());
            RoomDto roomDto = ObjectMapperUtils.toDto(optional.get(), RoomDto.class);
            dtos.add(roomDto);
        }
        return dtos;
    }

    @Override
    public RoomDto updateOneRoom(RoomDto roomDto) throws UserValidateException {
        Optional<Room> optional = roomRepository.findById(roomDto.getId());
        if (optional.isEmpty()) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target"));
        }
        if (!roomDto.getRoomCode().startsWith("PTIT_")) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.code.room"));
        }
        if (roomDto.getQuantity() <= 0) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.quantity.room"));
        }
        if (roomDto.getStatus() == 0) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.system.target.not.exist"));
        }
        Room room = ObjectMapperUtils.toEntity(roomDto, Room.class);
        optional.get().setRoomCode(room.getRoomCode());
        optional.get().setRoomType(room.getRoomType());
        optional.get().setAmount(room.getAmount());
        optional.get().setQuantity(room.getQuantity());
        roomRepository.save(optional.get());
        RoomDto updateDto = ObjectMapperUtils.toDto(room, RoomDto.class);
        return updateDto;
    }

    @Override
    public Map<String, Object> filterByRoomCode(PageDto pageDto, String roomCode) {
        Pageable pageable = PageRequest.of(pageDto.getOffset(), pageDto.getLimit(), Sort.by("roomCode"));
        Page<Room> roomPage = roomRepository.filterByCode(pageable, roomCode);
        customizePagination(roomPage);
        return resultPage;
    }

    private void customizePagination(Page<Room> page) {
        List<Room> roomList = page.getContent();
        List<RoomDto> dtos = ObjectMapperUtils.toDto(roomList, RoomDto.class);
        resultPage.put("data", dtos);
        resultPage.put("totalPage", page.getSize());
        resultPage.put("currentPage", page.getNumber());
        resultPage.put("totalItems", page.getTotalElements());
        resultPage.put("totalPages", page.getTotalPages());
    }

    private Room validateRoomData(RoomDto roomDto) throws UserValidateException, BadRequestException {
        if (roomDto.getRoomCode().matches("^[PTIT_]")) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.code.room"));
        }
        if (roomDto.getQuantity() <= 0) {
            throw new UserValidateException(MessageBundle.getMessage("dormitory.message.object.quantity.room"));
        }
        if (roomRepository.existsByRoomCode(roomDto.getRoomCode())) {
            throw new UserValidateException((MessageBundle.getMessage("dormitory.message.object.roomCode.room")));
        }
        if (roomDto.getQuantity().toString().matches(RegexContant.NUMBER_CODE_REGEX)
                && roomDto.getStatus().toString().matches(RegexContant.NUMBER_CODE_REGEX)) {
            throw new BadRequestException(MessageBundle.getMessage("dormitory.message.system.number"));
        }
        Room room = ObjectMapperUtils.toEntity(roomDto, Room.class);
        return room;
    }

}
