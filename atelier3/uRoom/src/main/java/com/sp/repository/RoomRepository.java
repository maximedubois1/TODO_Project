package com.sp.repository;

import com.sp.model.RoomEntity;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, String> {

    Optional<RoomEntity> findByName(String name);

    @Query("SELECT r FROM RoomEntity r WHERE r.winnerID IS NULL")
    List<RoomEntity> AvailableRooms();
}
