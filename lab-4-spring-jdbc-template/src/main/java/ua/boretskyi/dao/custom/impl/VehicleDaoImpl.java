package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.VehicleDao;
import ua.boretskyi.model.Vehicle;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleDaoImpl implements VehicleDao {

    public static final String FIND_ALL = "SELECT * FROM vehicle";
    public static final String FIND_BY_ID = "SELECT * FROM vehicle WHERE id=?";
    public static final String FIND_BY_SERIAL_NUMBER = "SELECT * FROM vehicle WHERE serial_number=?";
    public static final String CREATE = "INSERT vehicle(brand, model, manufacturing_date, licence_plate_number, serial_number, vehicle_type_id, weight_in_kilos) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE vehicle SET brand=?, model=?, manufacturing_date=?, licence_plate_number=?, serial_number=?, vehicle_type_id=?, weight_in_kilos=?  WHERE id=?";
    public static final String DELETE = "DELETE FROM vehicle WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public VehicleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Vehicle> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Vehicle.class));
    }

    @Override
    public Optional<Vehicle> findById(Integer id) {
        Optional<Vehicle> vehicle;
        try {
            vehicle = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(Vehicle.class), id));
        } catch (EmptyResultDataAccessException e) {
            vehicle = Optional.empty();
        }
        return vehicle;
    }

    @Override
    public Optional<Vehicle> findBySerialNumber(String serialNumber) {
        Optional<Vehicle> vehicle;
        try {
            vehicle = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_SERIAL_NUMBER, BeanPropertyRowMapper.newInstance(Vehicle.class), serialNumber));
        } catch (EmptyResultDataAccessException e) {
            vehicle = Optional.empty();
        }
        return vehicle;
    }

    @Override
    public Optional<Vehicle> create(Vehicle entity) {
        jdbcTemplate.update(CREATE, entity.getBrand(), entity.getModel(), entity.getManufacturingDate(),
                entity.getLicencePlateNumber(), entity.getSerialNumber(), entity.getVehicleTypeId(), entity.getWeightInKilos());
        return findBySerialNumber(entity.getSerialNumber());
    }

    @Override
    public Optional<Vehicle> update(Integer id, Vehicle entity) {
        jdbcTemplate.update(UPDATE, entity.getBrand(), entity.getModel(), entity.getManufacturingDate(),
                entity.getLicencePlateNumber(), entity.getSerialNumber(), entity.getVehicleTypeId(), entity.getWeightInKilos(), id);
        return findBySerialNumber(entity.getSerialNumber());
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }
}
