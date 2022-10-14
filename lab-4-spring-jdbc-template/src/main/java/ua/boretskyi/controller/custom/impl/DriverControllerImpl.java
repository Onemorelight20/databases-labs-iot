package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.DriverController;
import ua.boretskyi.model.Company;
import ua.boretskyi.model.Driver;
import ua.boretskyi.service.custom.DriverService;

import java.util.List;
import java.util.Optional;

@Controller
public class DriverControllerImpl implements DriverController {

    public final DriverService driverService;

    public DriverControllerImpl(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return driverService.findById(id);
    }

    @Override
    public Optional<Driver> create(Driver entity) {
        return driverService.create(entity);
    }

    @Override
    public Optional<Driver> update(Integer id, Driver entity) {
        return driverService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return driverService.delete(id);
    }

    @Override
    public Optional<Driver> findByPhoneNumber(String phoneNumber) {
        return driverService.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Company> findCompany(Driver driver) {
        return driverService.findCompany(driver);
    }
}
