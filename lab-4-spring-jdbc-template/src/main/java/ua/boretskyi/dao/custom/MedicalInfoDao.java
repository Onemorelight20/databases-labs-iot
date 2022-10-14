package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.MedicalInfo;

import java.util.Optional;

public interface MedicalInfoDao extends GeneralDao<MedicalInfo, Integer> {
    Optional<MedicalInfo> findLatestInfoForDriverWithId(Integer driverId);
    Optional<MedicalInfo> findLatestInfoForDoctorWithId(Integer doctorId);
    Optional<MedicalInfo> findLatestInfoForDriverAndDoctorWithIds(Integer driverId, Integer doctorId);
}
