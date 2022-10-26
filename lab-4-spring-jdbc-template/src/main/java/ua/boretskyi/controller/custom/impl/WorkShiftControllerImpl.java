package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.WorkShiftController;
import ua.boretskyi.model.WorkShift;
import ua.boretskyi.service.custom.WorkShiftService;

import java.util.List;
import java.util.Optional;

@Controller
public class WorkShiftControllerImpl implements WorkShiftController {

    private final WorkShiftService workShiftService;

    public WorkShiftControllerImpl(WorkShiftService workShiftService){
        this.workShiftService = workShiftService;
    }

    @Override
    public List<WorkShift> findAll() {
        return workShiftService.findAll();
    }

    @Override
    public Optional<WorkShift> findById(Integer id) {
        return workShiftService.findById(id);
    }

    @Override
    public Optional<WorkShift> create(WorkShift entity) {
        return workShiftService.create(entity);
    }

    @Override
    public Optional<WorkShift> update(Integer id, WorkShift entity) {
        return workShiftService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return workShiftService.delete(id);
    }

    @Override
    public List<WorkShift> getWorkShiftsOfDriverWithId(Integer driverId) {
        return workShiftService.getWorkShiftsOfDriverWithId(driverId);
    }

    @Override
    public List<WorkShift> getWorkShiftsOfVehicleWithId(Integer vehicleId) {
        return workShiftService.getWorkShiftsOfVehicleWithId(vehicleId);
    }
}
