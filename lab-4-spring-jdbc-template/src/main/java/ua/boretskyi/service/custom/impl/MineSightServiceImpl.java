package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.MineSightDao;
import ua.boretskyi.model.MineSight;
import ua.boretskyi.service.custom.MineSightService;

import java.util.List;
import java.util.Optional;

@Service
public class MineSightServiceImpl implements MineSightService {

    private final MineSightDao mineSightDao;

    public MineSightServiceImpl(MineSightDao mineSightDao) {
        this.mineSightDao = mineSightDao;
    }

    @Override
    public List<MineSight> findAll() {
        return mineSightDao.findAll();
    }

    @Override
    public Optional<MineSight> findById(Integer id) {
        return mineSightDao.findById(id);
    }

    @Override
    public Optional<MineSight> create(MineSight entity) {
        return mineSightDao.create(entity);
    }

    @Override
    public Optional<MineSight> update(Integer id, MineSight entity) {
        return mineSightDao.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return mineSightDao.delete(id);
    }

    @Override
    public List<MineSight> getMineSightsOfCompanyWithId(Integer companyId) {
        return mineSightDao.getMineSightsOfCompanyWithId(companyId);
    }

    @Override
    public Optional<MineSight> findByTitle(String title) {
        return mineSightDao.findByTitle(title);
    }
}
