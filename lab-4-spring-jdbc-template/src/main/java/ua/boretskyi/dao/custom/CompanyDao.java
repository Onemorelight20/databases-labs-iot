package ua.boretskyi.dao.custom;

import ua.boretskyi.dao.GeneralDao;
import ua.boretskyi.model.Company;

import java.util.Optional;

public interface CompanyDao extends GeneralDao<Company, Integer> {
    Optional<Company> findByTitle(String title);
}