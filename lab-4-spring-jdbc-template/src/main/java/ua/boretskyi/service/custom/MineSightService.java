package ua.boretskyi.service.custom;

import ua.boretskyi.model.MineSight;
import ua.boretskyi.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface MineSightService extends GeneralService<MineSight, Integer> {
    List<MineSight> getMineSightsOfCompanyWithId(Integer companyId);
    Optional<MineSight> findByTitle(String title);
}
