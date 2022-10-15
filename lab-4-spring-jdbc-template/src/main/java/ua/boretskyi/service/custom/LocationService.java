package ua.boretskyi.service.custom;


import ua.boretskyi.model.Location;
import ua.boretskyi.service.GeneralService;

import java.util.List;

public interface LocationService extends GeneralService<Location, Integer> {
    List<Location> getAllLocationOfVehicleWIthId(Integer vehicleId);
}
