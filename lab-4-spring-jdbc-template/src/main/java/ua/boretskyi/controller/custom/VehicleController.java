package ua.boretskyi.controller.custom;

import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.Vehicle;

import java.util.Optional;

public interface VehicleController extends GeneralController<Vehicle, Integer> {
    Optional<Vehicle> findBySerialNumber(String serialNumber);
}
