package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.Vehicle;

import java.util.Optional;

public interface VehicleDao extends GeneralDao<Vehicle, Integer> {
    Optional<Vehicle> findBySerialNumber(String serialNumber);
}
