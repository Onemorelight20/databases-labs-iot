package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.SensorDao;
import ua.boretskyi.model.Sensor;

import java.util.List;
import java.util.Optional;

@Repository
public class SensorDaoImpl implements SensorDao {

    public static final String FIND_ALL = "SELECT * FROM sensor";
    public static final String FIND_BY_ID = "SELECT * FROM sensor WHERE id=?";
    public static final String FIND_BY_VEHICLE = "SELECT * FROM sensor WHERE vehicle_id=?";
    public static final String CREATE = "INSERT sensor(brand, model, date_installed, vehicle_id) VALUES (?,?,?,?)";
    public static final String UPDATE = "UPDATE sensor SET brand=?, model=?, date_installed=?, vehicle_id=? WHERE id=?";
    public static final String DELETE = "DELETE FROM sensor WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public SensorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sensor> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Sensor.class));
    }

    @Override
    public Optional<Sensor> findById(Integer id) {
        Optional<Sensor> sensor;
        try {
            sensor = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(Sensor.class), id));
        } catch (EmptyResultDataAccessException e) {
            sensor = Optional.empty();
        }
        return sensor;
    }

    @Override
    public Optional<Sensor> create(Sensor entity) {
        int rows = jdbcTemplate.update(CREATE, entity.getBrand(), entity.getModel(), entity.getDateInstalled(), entity.getVehicleId());
        return rows == 1 ? Optional.of(entity) : Optional.empty();
    }

    @Override
    public Optional<Sensor> update(Integer id, Sensor entity) {
        int rows = jdbcTemplate.update(UPDATE, entity.getBrand(), entity.getModel(), entity.getDateInstalled(), entity.getVehicleId(), id);
        return rows == 1 ? Optional.of(entity) : Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public List<Sensor> getVehicleSensors(Integer id) {
        return jdbcTemplate.query(FIND_BY_VEHICLE, BeanPropertyRowMapper.newInstance(Sensor.class), id);
    }
}
