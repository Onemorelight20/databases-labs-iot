package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.FatigueLevelEntity;
import ua.boretskyi.dto.FatigueLevelDto;
import ua.boretskyi.dto.assembler.FatigueLevelDtoAssembler;
import ua.boretskyi.service.FatigueLevelService;

import java.util.List;

@RestController
@RequestMapping(value = "api/fatigueLevels")
public class FatigueLevelController {
    private final FatigueLevelService fatigueLevelService;
    private final FatigueLevelDtoAssembler fatigueLevelDtoAssembler;

    public FatigueLevelController(FatigueLevelService fatigueLevelService, FatigueLevelDtoAssembler fatigueLevelDtoAssembler) {
        this.fatigueLevelService = fatigueLevelService;
        this.fatigueLevelDtoAssembler = fatigueLevelDtoAssembler;
    }

    @GetMapping(value = "/{fatigueLevelId}")
    public ResponseEntity<FatigueLevelDto> getFatigueLevel(@PathVariable String fatigueLevelId) {
        FatigueLevelEntity fatigueLevelEntity = fatigueLevelService.findById(fatigueLevelId);
        FatigueLevelDto fatigueLevelDto = fatigueLevelDtoAssembler.toModel(fatigueLevelEntity);
        return new ResponseEntity<>(fatigueLevelDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<FatigueLevelDto>> getAllFatigueLevels() {
        List<FatigueLevelEntity> fatigueLevelEntityList = fatigueLevelService.findAll();
        CollectionModel<FatigueLevelDto> fatigueLevelDtos = fatigueLevelDtoAssembler.toCollectionModel(fatigueLevelEntityList);
        return new ResponseEntity<>(fatigueLevelDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FatigueLevelDto> addFatigueLevel(@RequestBody FatigueLevelEntity fatigueLevelEntity) {
        FatigueLevelEntity newFatigueLevelEntity = fatigueLevelService.create(fatigueLevelEntity);
        FatigueLevelDto fatigueLevelDto = fatigueLevelDtoAssembler.toModel(newFatigueLevelEntity);
        return new ResponseEntity<>(fatigueLevelDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{fatigueLevelId}")
    public ResponseEntity<?> updateFatigueLevel(@RequestBody FatigueLevelEntity fatigueLevelEntity, @PathVariable String fatigueLevelId) {
        fatigueLevelService.update(fatigueLevelId, fatigueLevelEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fatigueLevelId}")
    public ResponseEntity<?> deleteFatigueLevel(@PathVariable String fatigueLevelId) {
        fatigueLevelService.delete(fatigueLevelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
