package com.scenarioautomation.demo.dtos;

import jakarta.validation.constraints.NotBlank;

public record InLampSimpleDTO(
        @NotBlank String name
) {}
