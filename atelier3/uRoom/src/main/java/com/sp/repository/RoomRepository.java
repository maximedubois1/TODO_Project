package com.sp.repository;

import com.sp.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, String> {

    Optional<RoomEntity> findByName(String name);
}
