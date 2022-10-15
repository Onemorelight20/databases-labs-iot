package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.FatigueMonitoringDao;
import ua.boretskyi.model.FatigueMonitoring;

import java.util.List;
import java.util.Optional;

@Repository
public class FatigueMonitoringDaoImpl implements FatigueMonitoringDao {

    public static final String FIND_ALL = "SELECT * FROM fatigue_monitoring";
    public static final String FIND_BY_ID = "SELECT * FROM fatigue_monitoring WHERE id=?";
    public static final String FIND_BY_DRIVER_ID = "SELECT * FROM fatigue_monitoring WHERE driver_id=? ORDER BY record_time DESC";
    public static final String FIND_BY_WORK_SHIFT_ID = "SELECT * FROM fatigue_monitoring WHERE work_shift_id=? ORDER BY record_time DESC";
    public static final String CREATE = "INSERT fatigue_monitoring" +
            "(driver_id, work_shift_id, is_critical, fatigue_level_title, location_id, vehicle_speed_km_per_h, record_time) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE fatigue_monitoring SET " +
            "driver_id=?, work_shift_id=?, is_critical=?, fatigue_level_title=?, location_id=?, " +
            "vehicle_speed_km_per_h=?, record_time=?  WHERE id=?";
    public static final String DELETE = "DELETE FROM fatigue_monitoring WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public FatigueMonitoringDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<FatigueMonitoring> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(FatigueMonitoring.class));
    }

    @Override
    public Optional<FatigueMonitoring> findById(Integer id) {
        Optional<FatigueMonitoring> fatigueMonitoring;
        try {
            fatigueMonitoring = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(FatigueMonitoring.class), id));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            fatigueMonitoring = Optional.empty();
        }
        return fatigueMonitoring;
    }

    @Override
    public Optional<FatigueMonitoring> create(FatigueMonitoring entity) {
        int res = jdbcTemplate.update(CREATE, entity.getDriverId(), entity.getWorkShiftId(), entity.getIsCritical(),
                entity.getFatigueLevelTitle(), entity.getLocationId(), entity.getVehicleSpeedKmPerH(), entity.getRecordTime());
        return res == 0 ? Optional.empty() : Optional.of(entity);
    }

    @Override
    public Optional<FatigueMonitoring> update(Integer id, FatigueMonitoring entity) {
        int res = jdbcTemplate.update(UPDATE, entity.getDriverId(), entity.getWorkShiftId(), entity.getIsCritical(),
                entity.getFatigueLevelTitle(), entity.getLocationId(), entity.getVehicleSpeedKmPerH(), entity.getRecordTime(), id);
        return res == 0 ? Optional.empty() : this.findById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public List<FatigueMonitoring> getRecordsForDriverWithId(Integer driverId) {
        return jdbcTemplate.query(FIND_BY_DRIVER_ID, BeanPropertyRowMapper.newInstance(FatigueMonitoring.class), driverId);
    }

    @Override
    public List<FatigueMonitoring> getRecordsForWorkShiftWithId(Integer workShiftId) {
        return jdbcTemplate.query(FIND_BY_WORK_SHIFT_ID, BeanPropertyRowMapper.newInstance(FatigueMonitoring.class), workShiftId);
    }
}
