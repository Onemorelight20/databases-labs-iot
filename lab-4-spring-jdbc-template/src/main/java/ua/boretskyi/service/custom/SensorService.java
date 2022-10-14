package ua.boretskyi.service.custom;


import org.springframework.stereotype.Service;
import ua.boretskyi.model.Sensor;
import ua.boretskyi.service.GeneralService;

import java.util.List;

public interface SensorService extends GeneralService<Sensor, Integer> {
    List<Sensor> getVehicleSensors(Integer id);
}
