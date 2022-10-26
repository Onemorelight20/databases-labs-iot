package ua.boretskyi.service.custom;

import ua.boretskyi.model.Company;
import ua.boretskyi.model.Driver;
import ua.boretskyi.service.GeneralService;

import java.util.Optional;

public interface DriverService extends GeneralService<Driver, Integer> {
    Optional<Driver> findByPhoneNumber(String phoneNumber);
    Optional<Company> findCompany(Driver driver);
}
