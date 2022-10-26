package ua.boretskyi.controller.custom;

import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.VehicleType;

import java.util.Optional;


public interface VehicleTypeController extends GeneralController<VehicleType, Integer> {
    Optional<VehicleType> findByType(String type);
}
