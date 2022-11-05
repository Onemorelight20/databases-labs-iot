package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.MineSightEntity;

@Repository
public interface MineSightRepository extends JpaRepository<MineSightEntity, Integer> {
}
