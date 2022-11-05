package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.FatigueLevelEntity;

@Repository
public interface FatigueLevelRepository extends JpaRepository<FatigueLevelEntity, String> {
}
