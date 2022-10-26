package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.DoctorDao;
import ua.boretskyi.model.Doctor;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorDaoImpl implements DoctorDao {

    public static final String FIND_ALL = "SELECT * FROM doctor";
    public static final String FIND_BY_ID = "SELECT * FROM doctor WHERE id=?";
    public static final String FIND_BY_PHONE_NUMBER = "SELECT * FROM doctor WHERE phone_number=?";
    public static final String CREATE = "INSERT doctor(name, surname, phone_number) VALUES (?, ?, ?)";
    public static final String UPDATE = "UPDATE doctor SET name=?, surname=?, phone_number=? WHERE id=?";
    public static final String DELETE = "DELETE FROM doctor WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public DoctorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Doctor> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Doctor.class));
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        Optional<Doctor> doctor;
        try {
            doctor = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(Doctor.class), id));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            doctor = Optional.empty();
        }
        return doctor;
    }

    @Override
    public Optional<Doctor> findByPhoneNumber(String phoneNumber) {
        Optional<Doctor> doctor;
        try {
            doctor = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_PHONE_NUMBER, BeanPropertyRowMapper.newInstance(Doctor.class), phoneNumber));
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            doctor = Optional.empty();
        }
        return doctor;
    }

    @Override
    public Optional<Doctor> create(Doctor entity) {
        jdbcTemplate.update(CREATE, entity.getName(), entity.getSurname(), entity.getPhoneNumber());
        return findByPhoneNumber(entity.getPhoneNumber());
    }

    @Override
    public Optional<Doctor> update(Integer id, Doctor entity) {
        jdbcTemplate.update(UPDATE, entity.getName(), entity.getSurname(), entity.getPhoneNumber(), id);
        return findByPhoneNumber(entity.getPhoneNumber());
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

}
