package ua.boretskyi.controller.custom;


import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.MedicalInfo;

import java.util.Optional;

public interface MedicalInfoController extends GeneralController<MedicalInfo, Integer> {
    Optional<MedicalInfo> findLatestInfoForDriverWithId(Integer driverId);
    Optional<MedicalInfo> findLatestInfoForDoctorWithId(Integer doctorId);
    Optional<MedicalInfo> findLatestInfoForDriverAndDoctorWithIds(Integer driverId, Integer doctorId);
}
