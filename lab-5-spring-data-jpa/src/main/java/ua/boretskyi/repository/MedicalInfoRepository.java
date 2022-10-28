package ua.boretskyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.boretskyi.domain.MedicalInfoEntity;

@Repository
public interface MedicalInfoRepository extends JpaRepository<MedicalInfoEntity, Integer> {
}
