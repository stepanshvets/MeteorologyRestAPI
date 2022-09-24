package com.example.services;

import com.example.models.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public Measurement findOne(int id) {
        Optional<Measurement> foundMeasurement = measurementsRepository.findById(id);
        return foundMeasurement.orElse(null);
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()));
        measurement.setTime(LocalDateTime.now());
    }

//    @Transactional
//    public void save(Measurement measurement, String sensorName) {
//        measurement.setTime(LocalDateTime.now());
//        measurement.setSensor(sensorsService.findByName(sensorName));
//        measurementsRepository.save(measurement);
//    }

    public int getRainyDaysCount() {
        return (int) measurementsRepository.findAll().stream().filter(m -> m.isRaining()).count();
    }
}
