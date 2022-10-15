package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.WorkShift;

import java.util.List;

public interface WorkShiftDao extends GeneralDao<WorkShift, Integer> {
    List<WorkShift> getWorkShiftsOfDriverWithId(Integer driverId);
    List<WorkShift> getWorkShiftsOfVehicleWithId(Integer vehicleId);
}
