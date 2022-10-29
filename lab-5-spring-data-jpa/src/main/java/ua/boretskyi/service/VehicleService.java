package ua.boretskyi.service;


import ua.boretskyi.domain.VehicleEntity;

import java.util.List;

public interface VehicleService extends GeneralService<VehicleEntity, Integer> {
    List<VehicleEntity> getVehicleEntitiesByVehicleTypeId(Integer vehicleTypeId);
}
