package ua.boretskyi.service.custom;


import ua.boretskyi.model.WorkShift;
import ua.boretskyi.service.GeneralService;

import java.util.List;

public interface WorkShiftService extends GeneralService<WorkShift, Integer> {
    List<WorkShift> getWorkShiftsOfDriverWithId(Integer driverId);
    List<WorkShift> getWorkShiftsOfVehicleWithId(Integer vehicleId);
}
