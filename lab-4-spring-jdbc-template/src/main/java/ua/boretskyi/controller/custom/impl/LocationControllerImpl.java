package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.LocationController;
import ua.boretskyi.model.Location;
import ua.boretskyi.service.custom.LocationService;

import java.util.List;
import java.util.Optional;

@Controller
public class LocationControllerImpl implements LocationController {

    private final LocationService locationService;

    public LocationControllerImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @Override
    public Optional<Location> findById(Integer id) {
        return locationService.findById(id);
    }

    @Override
    public Optional<Location> create(Location entity) {
        return locationService.create(entity);
    }

    @Override
    public Optional<Location> update(Integer id, Location entity) {
        return locationService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return locationService.delete(id);
    }

    @Override
    public List<Location> getAllLocationOfVehicleWithId(Integer vehicleId) {
        return locationService.getAllLocationOfVehicleWIthId(vehicleId);
    }
}
