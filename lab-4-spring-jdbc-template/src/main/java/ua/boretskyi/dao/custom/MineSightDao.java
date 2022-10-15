package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.MineSight;

import java.util.List;
import java.util.Optional;

public interface MineSightDao extends GeneralDao<MineSight, Integer> {
    List<MineSight> getMineSightsOfCompanyWithId(Integer companyId);
    Optional<MineSight> findByTitle(String title);
}
