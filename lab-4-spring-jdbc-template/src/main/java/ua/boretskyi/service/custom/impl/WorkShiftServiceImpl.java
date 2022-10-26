package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.WorkShiftDao;
import ua.boretskyi.model.WorkShift;
import ua.boretskyi.service.custom.WorkShiftService;

import java.util.List;
import java.util.Optional;

@Service
public class WorkShiftServiceImpl implements WorkShiftService {

    private final WorkShiftDao workShiftDao;

    public WorkShiftServiceImpl(WorkShiftDao workShiftDao) {
        this.workShiftDao = workShiftDao;
    }

    @Override
    public List<WorkShift> findAll() {
        return workShiftDao.findAll();
    }

    @Override
    public Optional<WorkShift> findById(Integer id) {
        return workShiftDao.findById(id);
    }

    @Override
    public Optional<WorkShift> create(WorkShift entity) {
        return workShiftDao.create(entity);
    }

    @Override
    public Optional<WorkShift> update(Integer id, WorkShift entity) {
        return workShiftDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return workShiftDao.delete(id);
    }

    @Override
    public List<WorkShift> getWorkShiftsOfDriverWithId(Integer driverId) {
        return workShiftDao.getWorkShiftsOfDriverWithId(driverId);
    }

    @Override
    public List<WorkShift> getWorkShiftsOfVehicleWithId(Integer vehicleId) {
        return workShiftDao.getWorkShiftsOfVehicleWithId(vehicleId);
    }
}
