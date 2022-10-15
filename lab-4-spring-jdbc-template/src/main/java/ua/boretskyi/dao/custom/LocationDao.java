package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.Location;

import java.util.List;

public interface LocationDao extends GeneralDao<Location, Integer> {
    List<Location> getAllLocationOfVehicleWIthId(Integer vehicleId);
}
