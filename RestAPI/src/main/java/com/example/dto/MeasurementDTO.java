package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MeasurementDTO {
    @NotNull(message = "Value should not be empty!")
    @Min(value = -100, message = "Value should more than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private float value;

    @NotNull(message = "Raining should not be empty!")
    private Boolean raining;

    private LocalDateTime time;

    @JsonProperty("sensor")
    @NotNull(message = "Sensor should not be empty!")
    private SensorDTO sensorDTO;

    public MeasurementDTO() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", time=" + time +
                ", sensorDTO=" + sensorDTO +
                '}';
    }
}
