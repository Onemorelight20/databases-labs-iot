package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.VehicleTypeController;
import ua.boretskyi.model.VehicleType;
import ua.boretskyi.service.custom.VehicleTypeService;

import java.util.List;
import java.util.Optional;

@Controller
public class VehicleTypeControllerImpl implements VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    public VehicleTypeControllerImpl(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @Override
    public List<VehicleType> findAll() {
        return vehicleTypeService.findAll();
    }

    @Override
    public Optional<VehicleType> findById(Integer id) {
        return vehicleTypeService.findById(id);
    }

    @Override
    public Optional<VehicleType> create(VehicleType entity) {
        return vehicleTypeService.create(entity);
    }

    @Override
    public Optional<VehicleType> update(Integer id, VehicleType entity) {
        return vehicleTypeService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return vehicleTypeService.delete(id);
    }

    @Override
    public Optional<VehicleType> findByType(String type) {
        return vehicleTypeService.findByType(type);
    }
}
