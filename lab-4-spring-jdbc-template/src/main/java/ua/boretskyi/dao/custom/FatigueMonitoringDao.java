package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.FatigueMonitoring;

import java.util.List;

public interface FatigueMonitoringDao extends GeneralDao<FatigueMonitoring, Integer> {
    List<FatigueMonitoring> getRecordsForDriverWithId(Integer driverId);
    List<FatigueMonitoring> getRecordsForWorkShiftWithId(Integer driverId);
}
