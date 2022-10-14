package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.DoctorController;
import ua.boretskyi.model.Doctor;
import ua.boretskyi.service.custom.DoctorService;

import java.util.List;
import java.util.Optional;

@Controller
public class DoctorControllerImpl implements DoctorController {

    private final DoctorService doctorService;

    public DoctorControllerImpl(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorService.findAll();
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        return doctorService.findById(id);
    }

    @Override
    public Optional<Doctor> create(Doctor entity) {
        return doctorService.create(entity);
    }

    @Override
    public Optional<Doctor> update(Integer id, Doctor entity) {
        return doctorService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return doctorService.delete(id);
    }

    @Override
    public Optional<Doctor> findByPhoneNumber(String phoneNumber) {
        return doctorService.findByPhoneNumber(phoneNumber);
    }
}
