package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.DoctorDao;
import ua.boretskyi.model.Doctor;
import ua.boretskyi.service.custom.DoctorService;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        return doctorDao.findById(id);
    }

    @Override
    public Optional<Doctor> create(Doctor entity) {
        return doctorDao.create(entity);
    }

    @Override
    public Optional<Doctor> update(Integer id, Doctor entity) {
        return doctorDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return doctorDao.delete(id);
    }

    @Override
    public Optional<Doctor> findByPhoneNumber(String phoneNumber) {
        return doctorDao.findByPhoneNumber(phoneNumber);
    }
}
