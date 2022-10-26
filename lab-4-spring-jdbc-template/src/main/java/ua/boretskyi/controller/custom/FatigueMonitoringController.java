package ua.boretskyi.controller.custom;

import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.FatigueMonitoring;

import java.util.List;

public interface FatigueMonitoringController extends GeneralController<FatigueMonitoring, Integer> {
    List<FatigueMonitoring> getRecordsForDriverWithId(Integer driverId);
    List<FatigueMonitoring> getRecordsForWorkShiftWithId(Integer driverId);
}
