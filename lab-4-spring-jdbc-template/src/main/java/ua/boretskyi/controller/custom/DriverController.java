package ua.boretskyi.controller.custom;

import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.Company;
import ua.boretskyi.model.Driver;

import java.util.Optional;

public interface DriverController extends GeneralController<Driver, Integer> {
    Optional<Driver> findByPhoneNumber(String phoneNumber);
    Optional<Company> findCompany(Driver driver);
}
