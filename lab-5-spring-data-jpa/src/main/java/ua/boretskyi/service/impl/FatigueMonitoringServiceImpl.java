package ua.boretskyi.service.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.domain.FatigueMonitoringEntity;
import ua.boretskyi.exception.EntityNotFoundException;
import ua.boretskyi.repository.FatigueMonitoringRepository;
import ua.boretskyi.service.FatigueMonitoringService;

import java.util.List;

@Service
public class FatigueMonitoringServiceImpl implements FatigueMonitoringService {

    private final FatigueMonitoringRepository fatigueMonitoringRepository;

    public FatigueMonitoringServiceImpl(FatigueMonitoringRepository fatigueMonitoringRepository) {
        this.fatigueMonitoringRepository = fatigueMonitoringRepository;
    }

    @Override
    public List<FatigueMonitoringEntity> findAll() {
        return fatigueMonitoringRepository.findAll();
    }

    @Override
    public FatigueMonitoringEntity findById(Integer id) {
        return fatigueMonitoringRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FatigueMonitoring", String.valueOf(id)));
    }

    @Override
    public FatigueMonitoringEntity create(FatigueMonitoringEntity entity) {
        fatigueMonitoringRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, FatigueMonitoringEntity entity) {
        FatigueMonitoringEntity fatigueMonitoringEntity = findById(id);
        fatigueMonitoringEntity.setFatigueLevelTitle(entity.getFatigueLevelTitle());
        fatigueMonitoringEntity.setDriverId(entity.getDriverId());
        fatigueMonitoringEntity.setMineSightId(entity.getMineSightId());
        fatigueMonitoringEntity.setRecordTime(entity.getRecordTime());
        fatigueMonitoringEntity.setVehicleId(entity.getVehicleId());
        fatigueMonitoringRepository.save(fatigueMonitoringEntity);
    }

    @Override
    public void delete(Integer id) {
        FatigueMonitoringEntity fatigueMonitoringEntity = findById(id);
        fatigueMonitoringRepository.delete(fatigueMonitoringEntity);
    }
}
