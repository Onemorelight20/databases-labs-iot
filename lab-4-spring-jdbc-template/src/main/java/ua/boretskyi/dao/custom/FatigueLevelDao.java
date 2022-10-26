package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.FatigueLevel;

import java.util.Optional;

public interface FatigueLevelDao extends GeneralDao<FatigueLevel, String> {
    Optional<FatigueLevel> findByTitle(String title);
}
