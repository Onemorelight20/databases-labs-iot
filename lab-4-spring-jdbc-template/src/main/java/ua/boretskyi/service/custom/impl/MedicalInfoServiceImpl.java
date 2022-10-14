package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.MedicalInfoDao;
import ua.boretskyi.model.MedicalInfo;
import ua.boretskyi.service.custom.MedicalInfoService;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalInfoServiceImpl implements MedicalInfoService {

    private final MedicalInfoDao medicalInfoDao;

    public MedicalInfoServiceImpl(MedicalInfoDao medicalInfoDao) {
        this.medicalInfoDao = medicalInfoDao;
    }

    @Override
    public List<MedicalInfo> findAll() {
        return medicalInfoDao.findAll();
    }

    @Override
    public Optional<MedicalInfo> findById(Integer id) {
        return medicalInfoDao.findById(id);
    }

    @Override
    public Optional<MedicalInfo> create(MedicalInfo entity) {
        return medicalInfoDao.create(entity);
    }

    @Override
    public Optional<MedicalInfo> update(Integer id, MedicalInfo entity) {
        return medicalInfoDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return medicalInfoDao.delete(id);
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDriverWithId(Integer driverId) {
        return medicalInfoDao.findLatestInfoForDriverWithId(driverId);
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDoctorWithId(Integer doctorId) {
        return medicalInfoDao.findLatestInfoForDoctorWithId(doctorId);
    }

    @Override
    public Optional<MedicalInfo> findLatestInfoForDriverAndDoctorWithIds(Integer driverId, Integer doctorId) {
        return medicalInfoDao.findLatestInfoForDriverAndDoctorWithIds(driverId, doctorId);
    }
}
