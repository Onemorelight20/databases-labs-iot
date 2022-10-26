package ua.boretskyi.dao.custom.impl;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.MedicalInfoDao;
import ua.boretskyi.model.MedicalInfo;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicalInfoDaoImpl implements MedicalInfoDao {
    private static final String FIND_ALL = "SELECT * FROM medical_info";
    private static final String FIND_BY_ID = "SELECT * FROM medical_info WHERE id=?";
    private static final String FIND_BY_DRIVER_ID_LATEST = "SELECT * FROM medical_info WHERE driver_id=? ORDER BY updated_at DESC LIMIT 1";
    private static final String FIND_BY_DOCTOR_ID_LATEST = "SELECT * FROM medical_info WHERE doctor_id=? ORDER BY updated_at DESC LIMIT 1";
    private static final String FIND_BY_DRIVER_AND_DOCTOR_IDs_LATEST =
            "SELECT * FROM medical_info WHERE driver_id=? AND doctor_id=? ORDER BY updated_at DESC LIMIT 1";
    private static final String CREATE = "INSERT medical_info(driver_id, sight_state, blood_type, doctor_id, updated_at) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE medical_info SET driver_id=?, sight_state=?, blood_type=?, doctor_id=?, updated_at=? WHERE id=?";
    private static final String DELETE = "DELETE FROM medical_info WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public MedicalInfoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MedicalInfo> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(MedicalInfo.class));
    }

    @Override
    public Optional<MedicalInfo> findById(Integer id) {
        Optional<MedicalInfo> medicalInfo;
        try {
            medicalInfo = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(MedicalInfo.class), id));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            medicalInfo = Optional.empty();
        }
        return medicalInfo;
    }

    @Override
    public Optional<MedicalInfo> create(MedicalInfo entity) {
        jdbcTemplate.update(CREATE, entity.getDriverId(), entity.getSightState(), entity.getBloodType(), entity.getDoctorId(), entity.getUpdatedAt());
        return findLatestInfoForDriverAndDoctorWithIds(entity.getDriverId(), entity.getDoctorId());
    }

    @Override
    public Optional<MedicalInfo> update(Integer id, MedicalInfo entity) {
        jdbcTemplate.update(UPDATE, entity.getDriverId(), entity.getSightState(), entity.getBloodType(), entity.getDoctorId(), entity.getUpdatedAt());
        return findById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDriverWithId(Integer driverId) {
        Optional<MedicalInfo> medicalInfo;
        try {
            medicalInfo = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_DRIVER_ID_LATEST, BeanPropertyRowMapper.newInstance(MedicalInfo.class), driverId));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            medicalInfo = Optional.empty();
        }
        return medicalInfo;
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDoctorWithId(Integer doctorId) {
        Optional<MedicalInfo> medicalInfo;
        try {
            medicalInfo = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_DOCTOR_ID_LATEST, BeanPropertyRowMapper.newInstance(MedicalInfo.class), doctorId));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            medicalInfo = Optional.empty();
        }
        return medicalInfo;
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDriverAndDoctorWithIds(Integer driverId, Integer doctorId) {
        Optional<MedicalInfo> medicalInfo;
        try {
            medicalInfo = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_DRIVER_AND_DOCTOR_IDs_LATEST, BeanPropertyRowMapper.newInstance(MedicalInfo.class), driverId, doctorId));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            medicalInfo = Optional.empty();
        }
        return medicalInfo;
    }
}
