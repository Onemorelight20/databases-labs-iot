package ua.boretskyi.service.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.domain.FatigueLevelEntity;
import ua.boretskyi.exception.EntityNotFoundException;
import ua.boretskyi.repository.FatigueLevelRepository;
import ua.boretskyi.service.FatigueLevelService;

import java.util.List;

@Service
public class FatigueLevelServiceImpl implements FatigueLevelService {

    private final FatigueLevelRepository fatigueLevelRepository;

    public FatigueLevelServiceImpl(FatigueLevelRepository fatigueLevelRepository) {
        this.fatigueLevelRepository = fatigueLevelRepository;
    }

    @Override
    public List<FatigueLevelEntity> findAll() {
        return fatigueLevelRepository.findAll();
    }

    @Override
    public FatigueLevelEntity findById(String s) {
        return fatigueLevelRepository.findById(s)
                .orElseThrow(() -> new EntityNotFoundException("FatigueLevel", s));
    }

    @Override
    public FatigueLevelEntity create(FatigueLevelEntity entity) {
        fatigueLevelRepository.save(entity);
        return entity;
    }

    @Override
    public void update(String s, FatigueLevelEntity entity) {
        FatigueLevelEntity fatigueLevelEntity = findById(s);
        fatigueLevelEntity.setLevelTitle(entity.getLevelTitle());
        fatigueLevelRepository.save(fatigueLevelEntity);
    }

    @Override
    public void delete(String s) {
        FatigueLevelEntity fatigueLevelEntity = findById(s);
        fatigueLevelRepository.delete(fatigueLevelEntity);
    }
}
