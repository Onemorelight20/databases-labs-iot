package ua.boretskyi.controller.custom;

import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.FatigueLevel;

import java.util.Optional;

public interface FatigueLevelController extends GeneralController<FatigueLevel, String> {
    Optional<FatigueLevel> findByTitle(String title);
}
