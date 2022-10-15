package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.FatigueMonitoringDao;
import ua.boretskyi.model.FatigueMonitoring;
import ua.boretskyi.service.custom.FatigueMonitoringService;

import java.util.List;
import java.util.Optional;

@Service
public class FatigueMonitoringServiceImpl implements FatigueMonitoringService {

    private final FatigueMonitoringDao fatigueMonitoringDao;

    public FatigueMonitoringServiceImpl(FatigueMonitoringDao fatigueMonitoringDao) {
        this.fatigueMonitoringDao = fatigueMonitoringDao;
    }

    @Override
    public List<FatigueMonitoring> findAll() {
        return fatigueMonitoringDao.findAll();
    }

    @Override
    public Optional<FatigueMonitoring> findById(Integer id) {
        return fatigueMonitoringDao.findById(id);
    }

    @Override
    public Optional<FatigueMonitoring> create(FatigueMonitoring entity) {
        return fatigueMonitoringDao.create(entity);
    }

    @Override
    public Optional<FatigueMonitoring> update(Integer id, FatigueMonitoring entity) {
        return fatigueMonitoringDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return fatigueMonitoringDao.delete(id);
    }

    @Override
    public List<FatigueMonitoring> getRecordsForDriverWithId(Integer driverId) {
        return fatigueMonitoringDao.getRecordsForDriverWithId(driverId);
    }

    @Override
    public List<FatigueMonitoring> getRecordsForWorkShiftWithId(Integer driverId) {
        return fatigueMonitoringDao.getRecordsForWorkShiftWithId(driverId);
    }
}
