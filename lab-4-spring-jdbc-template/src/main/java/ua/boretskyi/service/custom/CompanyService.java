package ua.boretskyi.service.custom;


import ua.boretskyi.model.Company;
import ua.boretskyi.service.GeneralService;

import java.util.Optional;

public interface CompanyService extends GeneralService<Company, Integer> {
    Optional<Company> findByTitle(String title);
}