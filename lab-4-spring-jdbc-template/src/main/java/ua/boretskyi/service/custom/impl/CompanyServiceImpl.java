package ua.boretskyi.service.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.CompanyDao;
import ua.boretskyi.model.Company;
import ua.boretskyi.service.custom.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao){
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Optional<Company> findById(Integer id) {
        return companyDao.findById(id);
    }

    @Override
    public Optional<Company> create(Company entity) {
        return companyDao.create(entity);
    }

    @Override
    public Optional<Company> update(Integer id, Company entity) {
        return companyDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return companyDao.delete(id);
    }

    @Override
    public Optional<Company> findByTitle(String title) {
        return companyDao.findByTitle(title);
    }
}
