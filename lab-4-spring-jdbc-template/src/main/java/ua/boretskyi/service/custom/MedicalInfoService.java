package ua.boretskyi.service.custom;


import ua.boretskyi.model.MedicalInfo;
import ua.boretskyi.service.GeneralService;

import java.util.Optional;

public interface MedicalInfoService extends GeneralService<MedicalInfo, Integer> {
    Optional<MedicalInfo> findLatestInfoForDriverWithId(Integer driverId);
    Optional<MedicalInfo> findLatestInfoForDoctorWithId(Integer doctorId);
    Optional<MedicalInfo> findLatestInfoForDriverAndDoctorWithIds(Integer driverId, Integer doctorId);
}
