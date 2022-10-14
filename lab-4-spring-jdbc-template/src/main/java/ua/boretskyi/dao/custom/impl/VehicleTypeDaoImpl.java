package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.VehicleTypeDao;
import ua.boretskyi.model.VehicleType;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleTypeDaoImpl implements VehicleTypeDao {

    public static final String FIND_ALL = "SELECT * FROM vehicle_type";
    public static final String FIND_BY_ID = "SELECT * FROM vehicle_type WHERE id=?";
    public static final String FIND_BY_TYPE = "SELECT * FROM vehicle_type WHERE type=?";
    public static final String CREATE = "INSERT vehicle_type(type) VALUES (?)";
    public static final String UPDATE = "UPDATE vehicle_type SET type=? WHERE id=?";
    public static final String DELETE = "DELETE FROM vehicle_type WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public VehicleTypeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<VehicleType> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(VehicleType.class));
    }

    @Override
    public Optional<VehicleType> findById(Integer id) {
        Optional<VehicleType> vehicleType;
        try {
            vehicleType = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(VehicleType.class), id));
        } catch (EmptyResultDataAccessException e) {
            vehicleType = Optional.empty();
        }
        return vehicleType;
    }

    @Override
    public Optional<VehicleType> findByType(String type) {
        Optional<VehicleType> vehicleType;
        try {
            vehicleType = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_TYPE, BeanPropertyRowMapper.newInstance(VehicleType.class), type));
        } catch (EmptyResultDataAccessException e) {
            vehicleType = Optional.empty();
        }
        return vehicleType;
    }

    @Override
    public Optional<VehicleType> create(VehicleType entity) {
        jdbcTemplate.update(CREATE, entity.getType());
        return findByType(entity.getType());
    }

    @Override
    public Optional<VehicleType> update(Integer id, VehicleType entity) {
        jdbcTemplate.update(UPDATE, entity.getType(), id);
        return findById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

}
