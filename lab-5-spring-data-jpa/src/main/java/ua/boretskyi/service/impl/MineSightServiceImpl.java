package ua.boretskyi.service.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.domain.MineSightEntity;
import ua.boretskyi.exception.EntityNotFoundException;
import ua.boretskyi.repository.MineSightRepository;
import ua.boretskyi.service.MineSightService;

import java.util.List;

@Service
public class MineSightServiceImpl implements MineSightService {

    private final MineSightRepository mineSightRepository;

    public MineSightServiceImpl(MineSightRepository mineSightRepository) {
        this.mineSightRepository = mineSightRepository;
    }

    @Override
    public List<MineSightEntity> findAll() {
        return mineSightRepository.findAll();
    }

    @Override
    public MineSightEntity findById(Integer id) {
        return mineSightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MineSight", String.valueOf(id)));
    }

    @Override
    public MineSightEntity create(MineSightEntity entity) {
        mineSightRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, MineSightEntity entity) {
        MineSightEntity mineSightEntity = findById(id);
        mineSightEntity.setTitle(entity.getTitle());
        mineSightEntity.setCity(entity.getCity());
        mineSightEntity.setCountry(entity.getCountry());
        mineSightEntity.setCompanyEntitySet(entity.getCompanyEntitySet());
        mineSightRepository.save(mineSightEntity);
    }

    @Override
    public void delete(Integer id) {
        MineSightEntity mineSightEntity = findById(id);
        mineSightRepository.delete(mineSightEntity);
    }
}
