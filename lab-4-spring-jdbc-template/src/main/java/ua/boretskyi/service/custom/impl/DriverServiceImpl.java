package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.DriverDao;
import ua.boretskyi.model.Company;
import ua.boretskyi.model.Driver;
import ua.boretskyi.service.custom.DriverService;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao){
        this.driverDao = driverDao;
    }

    @Override
    public List<Driver> findAll() {
        return driverDao.findAll();
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return driverDao.findById(id);
    }

    @Override
    public Optional<Driver> create(Driver entity) {
        return driverDao.create(entity);
    }

    @Override
    public Optional<Driver> update(Integer id, Driver entity) {
        return driverDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return driverDao.delete(id);
    }

    @Override
    public Optional<Driver> findByPhoneNumber(String phoneNumber) {
        return driverDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Company> findCompany(Driver driver) {
        return driverDao.findCompany(driver);
    }
}
