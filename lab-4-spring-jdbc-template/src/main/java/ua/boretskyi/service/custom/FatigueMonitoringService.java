package ua.boretskyi.service.custom;

import ua.boretskyi.model.FatigueMonitoring;
import ua.boretskyi.service.GeneralService;

import java.util.List;

public interface FatigueMonitoringService extends GeneralService<FatigueMonitoring, Integer> {
    List<FatigueMonitoring> getRecordsForDriverWithId(Integer driverId);
    List<FatigueMonitoring> getRecordsForWorkShiftWithId(Integer driverId);
}
