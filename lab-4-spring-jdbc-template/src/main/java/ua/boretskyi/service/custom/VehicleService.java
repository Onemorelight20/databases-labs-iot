package ua.boretskyi.service.custom;

import ua.boretskyi.model.Vehicle;
import ua.boretskyi.service.GeneralService;

import java.util.Optional;

public interface VehicleService extends GeneralService<Vehicle, Integer> {
    Optional<Vehicle> findBySerialNumber(String serialNumber);
}
