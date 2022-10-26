package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.FatigueMonitoringController;
import ua.boretskyi.model.FatigueMonitoring;
import ua.boretskyi.service.custom.FatigueMonitoringService;

import java.util.List;
import java.util.Optional;

@Controller
public class FatigueMonitoringControllerImpl implements FatigueMonitoringController {

    private final FatigueMonitoringService fatigueMonitoringService;

    public FatigueMonitoringControllerImpl(FatigueMonitoringService fatigueMonitoringService) {
        this.fatigueMonitoringService = fatigueMonitoringService;
    }

    @Override
    public List<FatigueMonitoring> findAll() {
        return fatigueMonitoringService.findAll();
    }

    @Override
    public Optional<FatigueMonitoring> findById(Integer id) {
        return fatigueMonitoringService.findById(id);
    }

    @Override
    public Optional<FatigueMonitoring> create(FatigueMonitoring entity) {
        return fatigueMonitoringService.create(entity);
    }

    @Override
    public Optional<FatigueMonitoring> update(Integer id, FatigueMonitoring entity) {
        return fatigueMonitoringService.update(id ,entity);
    }

    @Override
    public boolean delete(Integer id) {
        return fatigueMonitoringService.delete(id);
    }

    @Override
    public List<FatigueMonitoring> getRecordsForDriverWithId(Integer driverId) {
        return fatigueMonitoringService.getRecordsForDriverWithId(driverId);
    }

    @Override
    public List<FatigueMonitoring> getRecordsForWorkShiftWithId(Integer workShiftId) {
        return fatigueMonitoringService.getRecordsForWorkShiftWithId(workShiftId);
    }
}
