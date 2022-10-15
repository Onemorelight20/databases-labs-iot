package ua.boretskyi.controller.custom;


import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.Location;

import java.util.List;

public interface LocationController extends GeneralController<Location, Integer> {
    List<Location> getAllLocationOfVehicleWithId(Integer vehicleId);
}
