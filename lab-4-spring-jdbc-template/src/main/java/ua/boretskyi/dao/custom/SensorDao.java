package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.Sensor;

import java.util.List;

public interface SensorDao extends GeneralDao<Sensor, Integer> {
    List<Sensor> getVehicleSensors(Integer id);
}
