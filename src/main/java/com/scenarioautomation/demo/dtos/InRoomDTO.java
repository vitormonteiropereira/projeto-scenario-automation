package com.scenarioautomation.demo.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record InRoomDTO(
        @NotBlank String name,
        @Valid List<InLampSimpleDTO> lamps
) {}
