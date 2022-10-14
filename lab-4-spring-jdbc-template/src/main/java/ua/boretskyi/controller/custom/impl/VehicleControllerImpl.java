package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.VehicleController;
import ua.boretskyi.model.Vehicle;
import ua.boretskyi.service.custom.VehicleService;

import java.util.List;
import java.util.Optional;

@Controller
public class VehicleControllerImpl implements VehicleController {

    private final VehicleService vehicleService;

    public VehicleControllerImpl(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @Override
    public Optional<Vehicle> findById(Integer id) {
        return vehicleService.findById(id);
    }

    @Override
    public Optional<Vehicle> create(Vehicle entity) {
        return vehicleService.create(entity);
    }

    @Override
    public Optional<Vehicle> update(Integer id, Vehicle entity) {
        return vehicleService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return vehicleService.delete(id);
    }

    @Override
    public Optional<Vehicle> findBySerialNumber(String serialNumber) {
        return vehicleService.findBySerialNumber(serialNumber);
    }
}
