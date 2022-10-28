package ua.boretskyi.service.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.domain.VehicleTypeEntity;
import ua.boretskyi.exception.EntityNotFoundException;
import ua.boretskyi.repository.VehicleTypeRepository;
import ua.boretskyi.service.VehicleTypeService;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeServiceImpl(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public List<VehicleTypeEntity> findAll() {
        return vehicleTypeRepository.findAll();
    }

    @Override
    public VehicleTypeEntity findById(Integer id) {
        return vehicleTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VehicleType", String.valueOf(id)));
    }

    @Override
    public VehicleTypeEntity create(VehicleTypeEntity entity) {
        vehicleTypeRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, VehicleTypeEntity entity) {
        VehicleTypeEntity vehicleTypeEntity = findById(id);
        vehicleTypeEntity.setType(entity.getType());
        vehicleTypeRepository.save(vehicleTypeEntity);
    }

    @Override
    public void delete(Integer id) {
        VehicleTypeEntity vehicleTypeEntity = findById(id);
        vehicleTypeRepository.delete(vehicleTypeEntity);
    }
}
