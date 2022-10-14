package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.VehicleType;

import java.util.Optional;

public interface VehicleTypeDao extends GeneralDao<VehicleType, Integer> {
    Optional<VehicleType> findByType(String type);
}
