package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.LocationDao;
import ua.boretskyi.model.Location;
import ua.boretskyi.model.Vehicle;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationDaoImpl implements LocationDao {

    public static final String FIND_ALL = "SELECT * FROM location";
    public static final String FIND_BY_ID = "SELECT * FROM location WHERE id=?";
    public static final String FIND_BY_VEHICLE_ID = "SELECT * FROM location WHERE vehicle_id=?";
    public static final String CREATE = "INSERT location(latitude, longitude, record_time, vehicle_id)  VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE location SET latitude=?, longitude=?, record_time=?, vehicle_id=? WHERE id=?";
    public static final String DELETE = "DELETE FROM location WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public LocationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Location> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Location.class));
    }

    @Override
    public Optional<Location> findById(Integer id) {
        Optional<Location> location;
        try {
            location = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(Location.class), id));
        } catch (EmptyResultDataAccessException e) {
            location = Optional.empty();
        }
        return location;
    }

    @Override
    public Optional<Location> create(Location entity) {
        int res = jdbcTemplate.update(CREATE, entity.getLatitude(), entity.getLongitude(), entity.getRecordTime(), entity.getVehicleId());
        return res == 0 ? Optional.empty() : Optional.of(entity);
    }

    @Override
    public Optional<Location> update(Integer id, Location entity) {
        jdbcTemplate.update(UPDATE, entity.getLatitude(), entity.getLongitude(), entity.getRecordTime(), entity.getVehicleId(), id);
        return this.findById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public List<Location> getAllLocationOfVehicleWIthId(Integer vehicleId) {
        return jdbcTemplate.query(FIND_BY_VEHICLE_ID, BeanPropertyRowMapper.newInstance(Location.class), vehicleId);
    }
}
