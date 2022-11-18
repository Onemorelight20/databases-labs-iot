package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.FatigueMonitoringEntity;

@Repository
public interface FatigueMonitoringRepository extends JpaRepository<FatigueMonitoringEntity, Integer> {
}
