package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.WorkShiftDao;
import ua.boretskyi.model.WorkShift;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkShiftDaoImpl implements WorkShiftDao {


    public static final String FIND_ALL = "SELECT * FROM work_shift";
    public static final String FIND_BY_ID = "SELECT * FROM work_shift WHERE id=?";
    public static final String FIND_BY_DRIVER_ID = "SELECT * FROM work_shift WHERE driver_id=? ORDER BY begin_at DESC";
    public static final String FIND_BY_VEHICLE_ID = "SELECT * FROM work_shift WHERE vehicle_id=? ORDER BY begin_at DESC";
    public static final String CREATE = "INSERT work_shift(driver_id, vehicle_id, medical_info_id, mine_sight_id, begin_at) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE work_shift SET driver_id=?, vehicle_id=?, medical_info_id=?, mine_sight_id=?, begin_at=? WHERE id=?";
    public static final String DELETE = "DELETE FROM work_shift WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public WorkShiftDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<WorkShift> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(WorkShift.class));
    }

    @Override
    public Optional<WorkShift> findById(Integer id) {
        Optional<WorkShift> workShift;
        try {
            workShift = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(WorkShift.class), id));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            workShift = Optional.empty();
        }
        return workShift;
    }

    @Override
    public Optional<WorkShift> create(WorkShift entity) {
        int res = jdbcTemplate.update(CREATE, entity.getDriverId(), entity.getVehicleId(), entity.getMedicalInfoId(), entity.getMineSightId(), entity.getBeginAt());
        return res == 0 ? Optional.empty() : Optional.of(entity);
    }

    @Override
    public Optional<WorkShift> update(Integer id, WorkShift entity) {
        jdbcTemplate.update(UPDATE, entity.getDriverId(), entity.getVehicleId(), entity.getMedicalInfoId(), entity.getMineSightId(), entity.getBeginAt(), id);
        return this.findById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public List<WorkShift> getWorkShiftsOfDriverWithId(Integer driverId) {
        return jdbcTemplate.query(FIND_BY_DRIVER_ID, BeanPropertyRowMapper.newInstance(WorkShift.class), driverId);
    }

    @Override
    public List<WorkShift> getWorkShiftsOfVehicleWithId(Integer vehicleId) {
        return jdbcTemplate.query(FIND_BY_VEHICLE_ID, BeanPropertyRowMapper.newInstance(WorkShift.class), vehicleId);
    }
}
