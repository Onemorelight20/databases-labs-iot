package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.FatigueMonitoringEntity;
import ua.boretskyi.dto.FatigueMonitoringDto;
import ua.boretskyi.dto.assembler.FatigueMonitoringDtoAssembler;
import ua.boretskyi.service.FatigueMonitoringService;

import java.util.List;

@RestController
@RequestMapping(value = "api/fatigueMonitorings")
public class FatigueMonitoringController {
    private final FatigueMonitoringService fatigueMonitoringService;
    private final FatigueMonitoringDtoAssembler fatigueMonitoringDtoAssembler;

    public FatigueMonitoringController(FatigueMonitoringService fatigueMonitoringService, FatigueMonitoringDtoAssembler fatigueMonitoringDtoAssembler) {
        this.fatigueMonitoringService = fatigueMonitoringService;
        this.fatigueMonitoringDtoAssembler = fatigueMonitoringDtoAssembler;
    }

    @GetMapping(value = "/{fatigueMonitoringId}")
    public ResponseEntity<FatigueMonitoringDto> getFatigueMonitoring(@PathVariable Integer fatigueMonitoringId) {
        FatigueMonitoringEntity fatigueMonitoringEntity = fatigueMonitoringService.findById(fatigueMonitoringId);
        FatigueMonitoringDto fatigueMonitoringDto = fatigueMonitoringDtoAssembler.toModel(fatigueMonitoringEntity);
        return new ResponseEntity<>(fatigueMonitoringDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<FatigueMonitoringDto>> getAllFatigueMonitorings() {
        List<FatigueMonitoringEntity> entityList = fatigueMonitoringService.findAll();
        CollectionModel<FatigueMonitoringDto> dtos = fatigueMonitoringDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FatigueMonitoringDto> addFatigueMonitoring(@RequestBody FatigueMonitoringEntity entity) {
        System.out.println("FATIGUE MONITORING" + entity);
        FatigueMonitoringEntity newEntity = fatigueMonitoringService.create(entity);
        FatigueMonitoringDto dto = fatigueMonitoringDtoAssembler.toModel(newEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{fatigueMonitoringId}")
    public ResponseEntity<?> updateFatigueMonitoring(@RequestBody FatigueMonitoringEntity entity, @PathVariable Integer fatigueMonitoringId) {
        fatigueMonitoringService.update(fatigueMonitoringId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fatigueMonitoringId}")
    public ResponseEntity<?> deleteFatigueMonitoring(@PathVariable Integer fatigueMonitoringId) {
        fatigueMonitoringService.delete(fatigueMonitoringId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
