package com.scenarioautomation.demo.controller;

import com.scenarioautomation.demo.domain.Lamp;
import com.scenarioautomation.demo.dtos.InLampDTO;
import com.scenarioautomation.demo.dtos.OutLampDTO;
import com.scenarioautomation.demo.service.LampService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lamps")
public class LampController {

    private final LampService service;

    public LampController(LampService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OutLampDTO> insertLamp (@Valid @RequestBody InLampDTO inLampDTO){
        var saved = service.insertLamp( inLampDTO.roomId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toOutDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLamp (@PathVariable("id") int id){
        service.deleteLamp(id);
        return ResponseEntity.status(HttpStatus.OK).body("Lamp successfully deleted");
    }

    @GetMapping
    public ResponseEntity<List<OutLampDTO>> getAllLamps(){
        List<OutLampDTO> lamps = service.listAllLamps().stream().map(this::toOutDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(lamps);
    }


    private OutLampDTO toOutDTO(Lamp lamp) {
        return new OutLampDTO(
                lamp.getId(),
                lamp.getRoom() != null ? lamp.getRoom().getId() : null
        );
    }

}
