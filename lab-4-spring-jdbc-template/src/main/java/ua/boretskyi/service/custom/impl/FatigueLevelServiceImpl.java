package ua.boretskyi.service.custom.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.dao.custom.FatigueLevelDao;
import ua.boretskyi.model.FatigueLevel;
import ua.boretskyi.service.custom.FatigueLevelService;

import java.util.List;
import java.util.Optional;

@Service
public class FatigueLevelServiceImpl implements FatigueLevelService {

    private final FatigueLevelDao fatigueLevelDao;

    public FatigueLevelServiceImpl(FatigueLevelDao fatigueLevelDao) {
        this.fatigueLevelDao = fatigueLevelDao;
    }

    @Override
    public List<FatigueLevel> findAll() {
        return fatigueLevelDao.findAll();
    }

    @Override
    public Optional<FatigueLevel> findById(String s) {
        return fatigueLevelDao.findById(s);
    }

    @Override
    public Optional<FatigueLevel> create(FatigueLevel entity) {
        return fatigueLevelDao.create(entity);
    }

    @Override
    public Optional<FatigueLevel> update(String s, FatigueLevel entity) {
        return fatigueLevelDao.update(s, entity);
    }

    @Override
    public boolean delete(String s) {
        return fatigueLevelDao.delete(s);
    }

    @Override
    public Optional<FatigueLevel> findByTitle(String title) {
        return fatigueLevelDao.findByTitle(title);
    }
}
