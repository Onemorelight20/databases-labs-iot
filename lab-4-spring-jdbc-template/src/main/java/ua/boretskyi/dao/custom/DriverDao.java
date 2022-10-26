package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.Company;
import ua.boretskyi.model.Doctor;
import ua.boretskyi.model.Driver;

import java.util.Optional;

public interface DriverDao extends GeneralDao<Driver, Integer> {
    Optional<Driver> findByPhoneNumber(String phoneNumber);
    Optional<Company> findCompany(Driver driver);
}
