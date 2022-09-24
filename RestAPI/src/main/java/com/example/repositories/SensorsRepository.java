package com.example.repositories;

import com.example.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    public Sensor findByName(String name);
}
