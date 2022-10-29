package ua.boretskyi.service.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.domain.VehicleEntity;
import ua.boretskyi.exception.EntityNotFoundException;
import ua.boretskyi.repository.VehicleRepository;
import ua.boretskyi.service.VehicleService;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public VehicleEntity findById(Integer id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle", String.valueOf(id)));
    }

    @Override
    public VehicleEntity create(VehicleEntity entity) {
        vehicleRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, VehicleEntity entity) {
        VehicleEntity vehicleEntity = findById(id);
        vehicleEntity.setVehicleType(entity.getVehicleType());
        vehicleEntity.setBrand(entity.getBrand());
        vehicleEntity.setModel(entity.getModel());
        vehicleEntity.setManufacturingDate(entity.getManufacturingDate());
        vehicleEntity.setSerialNumber(entity.getSerialNumber());
        vehicleRepository.save(vehicleEntity);
    }

    @Override
    public void delete(Integer id) {
        VehicleEntity vehicleEntity = findById(id);
        vehicleRepository.delete(vehicleEntity);
    }

    @Override
    public List<VehicleEntity> getVehicleEntitiesByVehicleTypeId(Integer vehicleTypeId) {
        return vehicleRepository.getVehicleEntitiesByVehicleTypeId(vehicleTypeId);
    }
}
