package com.scenarioautomation.demo.repository;

import com.scenarioautomation.demo.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {}
