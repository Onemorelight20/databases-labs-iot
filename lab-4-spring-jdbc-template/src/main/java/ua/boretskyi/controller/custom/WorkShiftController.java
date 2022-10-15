package ua.boretskyi.controller.custom;

import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.WorkShift;

import java.util.List;

public interface WorkShiftController extends GeneralController<WorkShift, Integer> {
    List<WorkShift> getWorkShiftsOfDriverWithId(Integer driverId);
    List<WorkShift> getWorkShiftsOfVehicleWithId(Integer vehicleId);
}
