package ua.boretskyi.service.custom;

import ua.boretskyi.model.FatigueLevel;
import ua.boretskyi.service.GeneralService;

import java.util.Optional;

public interface FatigueLevelService extends GeneralService<FatigueLevel, String> {
    Optional<FatigueLevel> findByTitle(String title);
}
