package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.FatigueLevelController;
import ua.boretskyi.model.FatigueLevel;
import ua.boretskyi.service.custom.FatigueLevelService;

import java.util.List;
import java.util.Optional;

@Controller
public class FatigueLevelControllerImpl implements FatigueLevelController {

    private final FatigueLevelService fatigueLevelService;

    public FatigueLevelControllerImpl(FatigueLevelService fatigueLevelService) {
        this.fatigueLevelService = fatigueLevelService;
    }

    @Override
    public List<FatigueLevel> findAll() {
        return fatigueLevelService.findAll();
    }

    @Override
    public Optional<FatigueLevel> findById(String s) {
        return fatigueLevelService.findById(s);
    }

    @Override
    public Optional<FatigueLevel> create(FatigueLevel entity) {
        return fatigueLevelService.create(entity);
    }

    @Override
    public Optional<FatigueLevel> update(String s, FatigueLevel entity) {
        return fatigueLevelService.update(s, entity);
    }

    @Override
    public boolean delete(String s) {
        return fatigueLevelService.delete(s);
    }

    @Override
    public Optional<FatigueLevel> findByTitle(String title) {
        return fatigueLevelService.findByTitle(title);
    }
}
