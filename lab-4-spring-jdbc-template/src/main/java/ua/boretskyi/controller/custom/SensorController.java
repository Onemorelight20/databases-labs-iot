package ua.boretskyi.controller.custom;


import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.Sensor;

import java.util.List;

public interface SensorController extends GeneralController<Sensor, Integer> {
    List<Sensor> getVehicleSensors(Integer id);
}
