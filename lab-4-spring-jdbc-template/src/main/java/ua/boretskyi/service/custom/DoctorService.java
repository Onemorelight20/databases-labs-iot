package ua.boretskyi.service.custom;

import ua.boretskyi.model.Doctor;
import ua.boretskyi.service.GeneralService;

import java.util.Optional;


public interface DoctorService extends GeneralService<Doctor, Integer> {
    Optional<Doctor> findByPhoneNumber(String phoneNumber);
}
