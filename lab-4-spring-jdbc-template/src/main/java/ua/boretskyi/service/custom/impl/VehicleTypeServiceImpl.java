package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.VehicleTypeDao;
import ua.boretskyi.model.VehicleType;
import ua.boretskyi.service.custom.VehicleTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeDao vehicleTypeDao;

    public VehicleTypeServiceImpl(VehicleTypeDao vehicleTypeDao) {
        this.vehicleTypeDao = vehicleTypeDao;
    }

    @Override
    public List<VehicleType> findAll() {
        return vehicleTypeDao.findAll();
    }

    @Override
    public Optional<VehicleType> findById(Integer id) {
        return vehicleTypeDao.findById(id);
    }

    @Override
    public Optional<VehicleType> create(VehicleType entity) {
        return vehicleTypeDao.create(entity);
    }

    @Override
    public Optional<VehicleType> update(Integer id, VehicleType entity) {
        return vehicleTypeDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return vehicleTypeDao.delete(id);
    }

    @Override
    public Optional<VehicleType> findByType(String type) {
        return vehicleTypeDao.findByType(type);
    }
}
