package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.Doctor;

import java.util.Optional;

public interface DoctorDao extends GeneralDao<Doctor, Integer> {
    Optional<Doctor> findByPhoneNumber(String phoneNumber);
}
