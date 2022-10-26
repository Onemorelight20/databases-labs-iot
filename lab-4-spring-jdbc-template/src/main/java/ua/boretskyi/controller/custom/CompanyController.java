package ua.boretskyi.controller.custom;


import ua.boretskyi.controller.GeneralController;
import ua.boretskyi.model.Company;

import java.util.Optional;

public interface CompanyController extends GeneralController<Company, Integer> {
    Optional<Company> findByTitle(String title);
}