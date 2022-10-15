package ua.boretskyi.controller.custom.impl;

import org.springframework.stereotype.Controller;
import ua.boretskyi.controller.custom.MineSightController;
import ua.boretskyi.model.MineSight;
import ua.boretskyi.service.custom.MineSightService;

import java.util.List;
import java.util.Optional;

@Controller
public class MineSightControllerImpl implements MineSightController {

    private final MineSightService mineSightService;

    public MineSightControllerImpl(MineSightService mineSightService) {
        this.mineSightService = mineSightService;
    }

    @Override
    public List<MineSight> findAll() {
        return mineSightService.findAll();
    }

    @Override
    public Optional<MineSight> findById(Integer id) {
        return mineSightService.findById(id);
    }

    @Override
    public Optional<MineSight> create(MineSight entity) {
        return mineSightService.create(entity);
    }

    @Override
    public Optional<MineSight> update(Integer id, MineSight entity) {
        return mineSightService.update(id, entity);
    }

    @Override
    public boolean delete(Integer id) {
        return mineSightService.delete(id);
    }

    @Override
    public List<MineSight> getMineSightsOfCompanyWithId(Integer companyId) {
        return mineSightService.getMineSightsOfCompanyWithId(companyId);
    }

    @Override
    public Optional<MineSight> findByTitle(String title) {
        return mineSightService.findByTitle(title);
    }
}
