package com.scenarioautomation.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InLampDTO(
        @NotNull Integer roomId
) {}
