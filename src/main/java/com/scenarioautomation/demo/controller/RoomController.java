package com.scenarioautomation.demo.controller;

import com.scenarioautomation.demo.domain.Lamp;
import com.scenarioautomation.demo.domain.Room;
import com.scenarioautomation.demo.dtos.InRoomDTO;
import com.scenarioautomation.demo.dtos.OutLampDTO;
import com.scenarioautomation.demo.dtos.OutRoomDTO;
import com.scenarioautomation.demo.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OutRoomDTO> insertRoom(@Valid @RequestBody InRoomDTO inRoomDTO) {
        Room room = new Room(inRoomDTO.name());
        if (inRoomDTO.lamps() != null) {
            inRoomDTO.lamps().forEach(ldto -> room.addLamp(new Lamp(ldto.name())));
        }
        Room saved = service.insertRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(toOutDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutRoomDTO> updateRoom (@PathVariable("id") int id, @Valid @RequestBody InRoomDTO inRoomDTO){
        var updated = service.updateRoom(id, inRoomDTO.name());
        return ResponseEntity.status(HttpStatus.OK).body(toOutDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRoom (@PathVariable("id") int id){
        service.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.OK).body("Room successfully deleted");
    }

    @GetMapping
    public ResponseEntity<List<OutRoomDTO>> getAllRooms (){
        List<OutRoomDTO> rooms = service.listAllRooms().stream().map(this::toOutDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutRoomDTO> getRoomById(@PathVariable("id") int id) {
        Room room = service.getRoomById(id);
        return ResponseEntity.status(HttpStatus.OK).body(toOutDTO(room));
    }

    private OutRoomDTO toOutDTO(Room room) {
        return new OutRoomDTO(
                room.getId(),
                room.getName(),
                room.getLamps().stream()
                        .map(l -> new OutLampDTO(l.getId(), l.getName()))
                        .toList()
        );
    }
}
