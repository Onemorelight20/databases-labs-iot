package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.DriverEntity;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Integer> {
}
