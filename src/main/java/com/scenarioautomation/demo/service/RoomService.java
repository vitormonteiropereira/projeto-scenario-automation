package com.scenarioautomation.demo.service;

import com.scenarioautomation.demo.domain.Room;
import com.scenarioautomation.demo.exceptions.RoomNotFoundException;
import com.scenarioautomation.demo.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Room insertRoom(Room room){
        return repository.save(room);
    }

    @Transactional
    public Room updateRoom(int id, String name){
        var current = repository.findById(id).orElseThrow(RoomNotFoundException::new);
        current.setName(name);
        return repository.save(current);
    }

    @Transactional
    public void deleteRoom(int id){
        if (!repository.existsById(id)){
            throw new RoomNotFoundException();
        }
        repository.deleteById(id);
    }

    @Transactional
    public List<Room> listAllRooms() {
        return repository.findAll(Sort.by("id"));
    }

    public Room getRoomById(int id){
        return repository.findById(id).orElseThrow(RoomNotFoundException::new);
    }
}
