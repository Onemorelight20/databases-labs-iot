package ua.boretskyi.controller.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.CompanyController;
import ua.boretskyi.model.Company;
import ua.boretskyi.service.custom.CompanyService;

import java.util.List;
import java.util.Optional;

@Controller
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyControllerImpl(CompanyService companyService){
        this.companyService = companyService;
    }

    @Override
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @Override
    public Optional<Company> findById(Integer id) {
        return companyService.findById(id);
    }

    @Override
    public Optional<Company> create(Company entity) {
        return companyService.create(entity);
    }

    @Override
    public Optional<Company> update(Integer id, Company entity) {
        return companyService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return companyService.delete(id);
    }

    @Override
    public Optional<Company> findByTitle(String title) {
        return companyService.findByTitle(title);
    }
}
