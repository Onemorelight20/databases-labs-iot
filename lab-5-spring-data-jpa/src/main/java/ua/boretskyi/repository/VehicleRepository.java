package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.VehicleEntity;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {
    List<VehicleEntity> getVehicleEntitiesByVehicleTypeId(Integer vehicleTypeId);
}
