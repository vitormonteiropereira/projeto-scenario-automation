package com.scenarioautomation.demo.repository;

import com.scenarioautomation.demo.domain.Lamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LampRepository extends JpaRepository<Lamp, Integer> {}
