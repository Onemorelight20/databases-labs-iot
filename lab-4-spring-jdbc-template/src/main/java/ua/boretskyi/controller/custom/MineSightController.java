package ua.boretskyi.controller.custom;

import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.MineSight;

import java.util.List;
import java.util.Optional;

public interface MineSightController extends GeneralController<MineSight, Integer> {
    List<MineSight> getMineSightsOfCompanyWithId(Integer companyId);
    Optional<MineSight> findByTitle(String title);
}
