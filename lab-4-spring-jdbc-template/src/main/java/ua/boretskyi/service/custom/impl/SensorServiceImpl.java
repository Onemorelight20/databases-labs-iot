package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.SensorDao;
import ua.boretskyi.model.Sensor;
import ua.boretskyi.service.custom.SensorService;

import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorDao sensorDao;

    public SensorServiceImpl(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @Override
    public List<Sensor> findAll() {
        return sensorDao.findAll();
    }

    @Override
    public Optional<Sensor> findById(Integer id) {
        return findById(id);
    }

    @Override
    public Optional<Sensor> create(Sensor entity) {
        return sensorDao.create(entity);
    }

    @Override
    public Optional<Sensor> update(Integer id, Sensor entity) {
        return sensorDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return sensorDao.delete(id);
    }

    @Override
    public List<Sensor> getVehicleSensors(Integer id) {
        return sensorDao.getVehicleSensors(id);
    }
}
