package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.VehicleTypeEntity;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Integer> {
}
