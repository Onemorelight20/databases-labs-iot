package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.LocationDao;
import ua.boretskyi.model.Location;
import ua.boretskyi.service.custom.LocationService;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public List<Location> findAll() {
        return locationDao.findAll();
    }

    @Override
    public Optional<Location> findById(Integer id) {
        return locationDao.findById(id);
    }

    @Override
    public Optional<Location> create(Location entity) {
        return locationDao.create(entity);
    }

    @Override
    public Optional<Location> update(Integer id, Location entity) {
        return locationDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return locationDao.delete(id);
    }

    @Override
    public List<Location> getAllLocationOfVehicleWIthId(Integer vehicleId) {
        return locationDao.getAllLocationOfVehicleWIthId(vehicleId);
    }
}
