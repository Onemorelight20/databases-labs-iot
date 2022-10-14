package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.VehicleDao;
import ua.boretskyi.model.Vehicle;
import ua.boretskyi.service.custom.VehicleService;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleDao vehicleDao;

    public VehicleServiceImpl(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    @Override
    public Optional<Vehicle> findById(Integer id) {
        return vehicleDao.findById(id);
    }

    @Override
    public Optional<Vehicle> create(Vehicle entity) {
        return vehicleDao.create(entity);
    }

    @Override
    public Optional<Vehicle> update(Integer id, Vehicle entity) {
        return vehicleDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return vehicleDao.delete(id);
    }

    @Override
    public Optional<Vehicle> findBySerialNumber(String serialNumber) {
        return vehicleDao.findBySerialNumber(serialNumber);
    }
}
