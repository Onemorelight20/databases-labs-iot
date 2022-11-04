package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.MineSightEntity;
import ua.boretskyi.dto.MineSightDto;
import ua.boretskyi.dto.assembler.MineSightDtoAssembler;
import ua.boretskyi.service.MineSightService;

import java.util.List;

@RestController
@RequestMapping(value = "api/mineSights")
public class MineSightController {
    private final MineSightService mineSightService;
    private final MineSightDtoAssembler mineSightDtoAssembler;

    public MineSightController(MineSightService mineSightService, MineSightDtoAssembler mineSightDtoAssembler) {
        this.mineSightService = mineSightService;
        this.mineSightDtoAssembler = mineSightDtoAssembler;
    }

    @GetMapping(value = "/{mineSightId}")
    public ResponseEntity<MineSightDto> getMineSight(@PathVariable Integer mineSightId) {
        MineSightEntity mineSightEntity = mineSightService.findById(mineSightId);
        MineSightDto fatigueMonitoringDto = mineSightDtoAssembler.toModel(mineSightEntity);
        return new ResponseEntity<>(fatigueMonitoringDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<MineSightDto>> getAllMineSights() {
        List<MineSightEntity> entityList = mineSightService.findAll();
        CollectionModel<MineSightDto> dtos = mineSightDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MineSightDto> addFatigueMonitoring(@RequestBody MineSightEntity entity) {
        MineSightEntity newEntity = mineSightService.create(entity);
        MineSightDto dto = mineSightDtoAssembler.toModel(newEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{mineSightId}")
    public ResponseEntity<?> updateMineSight(@RequestBody MineSightEntity entity, @PathVariable Integer mineSightId) {
        mineSightService.update(mineSightId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{mineSightId}")
    public ResponseEntity<?> deleteFatigueMonitoring(@PathVariable Integer mineSightId) {
        mineSightService.delete(mineSightId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
