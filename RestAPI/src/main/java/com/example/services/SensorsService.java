package com.example.services;

import com.example.models.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.repositories.SensorsRepository;
import com.example.util.NotCreatedException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public Sensor findOne(int id) {
        Optional<Sensor> foundSensor = sensorsRepository.findById(id);
        return foundSensor.orElse(null);
    }

    public Sensor findByName(String name){
        return sensorsRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        if (sensorsRepository.findByName(sensor.getName()) != null)
            throw new NotCreatedException("Sensor with name \"" + sensor.getName() +
                    "\" has already exists");
        sensor.setCreatedAt(LocalDateTime.now());
        sensorsRepository.save(sensor);
    }
}
