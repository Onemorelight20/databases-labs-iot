package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.DriverDao;
import ua.boretskyi.model.Company;
import ua.boretskyi.model.Driver;

import java.util.List;
import java.util.Optional;

@Repository
public class DriverDaoImpl implements DriverDao {
    public static final String FIND_ALL = "SELECT * FROM driver";
    public static final String FIND_BY_ID = "SELECT * FROM driver WHERE id=?";
    public static final String FIND_BY_PHONE_NUMBER = "SELECT * FROM driver WHERE phone_number=?";
    public static final String FIND_COMPANY = "SELECT * FROM company WHERE id=?";
    public static final String CREATE = "INSERT driver(name, surname, company_id, phone_number) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE driver SET name=?, surname=?, company_id=?, phone_number=? WHERE id=?";
    public static final String DELETE = "DELETE FROM driver WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public DriverDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Driver> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Driver.class));
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        Optional<Driver> driver;
        try {
            driver = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(Driver.class), id));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            driver = Optional.empty();
        }
        return driver;
    }

    @Override
    public Optional<Driver> findByPhoneNumber(String phoneNumber) {
        Optional<Driver> driver;
        try {
            driver = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_PHONE_NUMBER, BeanPropertyRowMapper.newInstance(Driver.class), phoneNumber));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            driver = Optional.empty();
        }
        return driver;
    }

    @Override
    public Optional<Company> findCompany(Driver driver) {
        Optional<Company> company;
        try {
            company = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_COMPANY, BeanPropertyRowMapper.newInstance(Company.class), driver.getCompanyId()));
        } catch (EmptyResultDataAccessException e) {
            company = Optional.empty();
        }
        return company;
    }

    @Override
    public Optional<Driver> create(Driver entity) {
        jdbcTemplate.update(CREATE, entity.getName(), entity.getSurname(), entity.getCompanyId(), entity.getPhoneNumber());
        return findByPhoneNumber(entity.getPhoneNumber());
    }

    @Override
    public Optional<Driver> update(Integer id, Driver entity) {
        jdbcTemplate.update(UPDATE, entity.getName(), entity.getSurname(), entity.getCompanyId(), entity.getPhoneNumber(), id);
        return findByPhoneNumber(entity.getPhoneNumber());
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }
}
