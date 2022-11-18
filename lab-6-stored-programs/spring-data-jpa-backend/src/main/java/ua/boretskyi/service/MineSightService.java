package ua.boretskyi.service;

import ua.boretskyi.domain.MineSightEntity;

public interface MineSightService extends GeneralService<MineSightEntity, Integer>{
    Integer insertWithProcedure(String country, String city, String title);
    void insertIntoCompanyMineSightMtoM(String companyTitle, String mineSightTitle);
}
