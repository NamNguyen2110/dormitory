package com.web.assgiment.dormitory.repository;

import com.web.assgiment.dormitory.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE r.roomCode LIKE CONCAT('%',:roomCode,'%') AND r.status = 1")
    Page<Room> filterByCode(Pageable pageable, @Param("roomCode") String roomId);

    @Query("SELECT r FROM Room r WHERE r.status = 1")
    Page<Room> getAllRoom(Pageable pageable);

    boolean existsByRoomCode(String roomCode);
}
