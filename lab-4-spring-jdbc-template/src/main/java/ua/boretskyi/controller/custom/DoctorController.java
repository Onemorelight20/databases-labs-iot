package ua.boretskyi.controller.custom;

import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.Doctor;

import java.util.Optional;


public interface DoctorController extends GeneralController<Doctor, Integer> {
    Optional<Doctor> findByPhoneNumber(String phoneNumber);
}
