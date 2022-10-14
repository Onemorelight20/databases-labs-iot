package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.MedicalInfoController;
import ua.boretskyi.model.MedicalInfo;
import ua.boretskyi.service.custom.MedicalInfoService;

import java.util.List;
import java.util.Optional;

@Controller
public class MedicalInfoControllerImpl implements MedicalInfoController {

    private final MedicalInfoService medicalInfoService;

    public MedicalInfoControllerImpl(MedicalInfoService medicalInfoService) {
        this.medicalInfoService = medicalInfoService;
    }

    @Override
    public List<MedicalInfo> findAll() {
        return medicalInfoService.findAll();
    }

    @Override
    public Optional<MedicalInfo> findById(Integer id) {
        return medicalInfoService.findById(id);
    }

    @Override
    public Optional<MedicalInfo> create(MedicalInfo entity) {
        return medicalInfoService.create(entity);
    }

    @Override
    public Optional<MedicalInfo> update(Integer id, MedicalInfo entity) {
        return medicalInfoService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return medicalInfoService.delete(id);
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDriverWithId(Integer driverId) {
        return medicalInfoService.findLatestInfoForDriverWithId(driverId);
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDoctorWithId(Integer doctorId) {
        return medicalInfoService.findLatestInfoForDoctorWithId(doctorId);
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDriverAndDoctorWithIds(Integer driverId, Integer doctorId) {
        return medicalInfoService.findLatestInfoForDriverAndDoctorWithIds(driverId, doctorId);
    }
}
