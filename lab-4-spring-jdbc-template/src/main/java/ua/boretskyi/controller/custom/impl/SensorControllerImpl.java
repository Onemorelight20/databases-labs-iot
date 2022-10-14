package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.SensorController;
import ua.boretskyi.model.Sensor;
import ua.boretskyi.service.custom.SensorService;

import java.util.List;
import java.util.Optional;

@Controller
public class SensorControllerImpl implements SensorController {

    private final SensorService sensorService;

    public SensorControllerImpl(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public List<Sensor> findAll() {
        return sensorService.findAll();
    }

    @Override
    public Optional<Sensor> findById(Integer id) {
        return findById(id);
    }

    @Override
    public Optional<Sensor> create(Sensor entity) {
        return sensorService.create(entity);
    }

    @Override
    public Optional<Sensor> update(Integer id, Sensor entity) {
        return sensorService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return sensorService.delete(id);
    }

    @Override
    public List<Sensor> getVehicleSensors(Integer id) {
        return sensorService.getVehicleSensors(id);
    }
}
