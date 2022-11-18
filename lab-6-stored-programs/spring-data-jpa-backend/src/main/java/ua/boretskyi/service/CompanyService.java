package ua.boretskyi.service;

import ua.boretskyi.domain.CompanyEntity;

public interface CompanyService extends GeneralService<CompanyEntity, Integer>{
    void insertTenCompanies();
    Integer getCompaniesIdSum();
    void createTwoTablesAndInsertDataDynamically();
}
