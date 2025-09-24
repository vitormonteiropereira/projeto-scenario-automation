package com.scenarioautomation.demo.service;

import com.scenarioautomation.demo.domain.Lamp;
import com.scenarioautomation.demo.domain.Room;
import com.scenarioautomation.demo.repository.LampRepository;
import com.scenarioautomation.demo.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.scenarioautomation.demo.exceptions.RoomNotFoundException;
import com.scenarioautomation.demo.exceptions.LampNotFoundException;

import java.util.List;

@Service
public class LampService {

    private final LampRepository lampRepository;
    private final RoomRepository roomRepository;

    public LampService(LampRepository lampRepository, RoomRepository roomRepository) {
        this.lampRepository = lampRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Lamp insertLamp(String name, Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(RoomNotFoundException::new);

        Lamp lamp = new Lamp(name);
        lamp.setRoom(room);
        return lampRepository.save(lamp);
    }

    @Transactional
    public Lamp updateLamp(int id, String name) {
        Lamp current = lampRepository.findById(id).orElseThrow(LampNotFoundException::new);
        current.setName(name);
        return lampRepository.save(current);
    }

    @Transactional
    public void deleteLamp(int id) {
        if (!lampRepository.existsById(id)) throw new LampNotFoundException();
        lampRepository.deleteById(id);
    }

    @Transactional
    public List<Lamp> listAllLamps() {
        return lampRepository.findAll(Sort.by("id"));
    }

    @Transactional
    public Lamp getLampById(int id) {
        return lampRepository.findById(id).orElseThrow(LampNotFoundException::new);
    }
}
