package ua.boretskyi.service.custom;

import ua.boretskyi.model.VehicleType;
import ua.boretskyi.service.GeneralService;

import java.util.Optional;

public interface VehicleTypeService extends GeneralService<VehicleType, Integer> {
    Optional<VehicleType> findByType(String type);
}
