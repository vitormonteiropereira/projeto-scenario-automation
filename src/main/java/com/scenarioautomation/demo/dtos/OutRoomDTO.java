package com.scenarioautomation.demo.dtos;

import java.util.List;

public record OutRoomDTO(
        Integer id,
        String name,
        List<OutLampDTO> lamps
) {}
