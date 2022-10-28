package ua.boretskyi.service.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.domain.MedicalInfoEntity;
import ua.boretskyi.exception.EntityNotFoundException;
import ua.boretskyi.repository.MedicalInfoRepository;
import ua.boretskyi.service.MedicalInfoService;

import java.util.List;

@Service
public class MedicalInfoServiceImpl implements MedicalInfoService {

    private final MedicalInfoRepository medicalInfoRepository;

    public MedicalInfoServiceImpl(MedicalInfoRepository medicalInfoRepository) {
        this.medicalInfoRepository = medicalInfoRepository;
    }

    @Override
    public List<MedicalInfoEntity> findAll() {
        return medicalInfoRepository.findAll();
    }

    @Override
    public MedicalInfoEntity findById(Integer id) {
        return medicalInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MedicalInfo", String.valueOf(id)));
    }

    @Override
    public MedicalInfoEntity create(MedicalInfoEntity entity) {
        medicalInfoRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, MedicalInfoEntity entity) {
        MedicalInfoEntity medicalInfoEntity = findById(id);
        medicalInfoEntity.setDriver(entity.getDriver());
        medicalInfoEntity.setSightState(entity.getSightState());
        medicalInfoEntity.setBloodType(entity.getBloodType());
        medicalInfoEntity.setUpdatedAt(entity.getUpdatedAt());
        medicalInfoRepository.save(medicalInfoEntity);
    }

    @Override
    public void delete(Integer id) {
        MedicalInfoEntity medicalInfoEntity = findById(id);
        medicalInfoRepository.delete(medicalInfoEntity);
    }
}
