package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.MedicalInfoEntity;
import ua.boretskyi.dto.MedicalInfoDto;
import ua.boretskyi.dto.assembler.MedicalInfoDtoAssembler;
import ua.boretskyi.service.MedicalInfoService;

import java.util.List;

@RestController
@RequestMapping(value = "api/medicalInfos")
public class MedicalInfoController {
    private final MedicalInfoService medicalInfoService;
    private final MedicalInfoDtoAssembler medicalInfoDtoAssembler;

    public MedicalInfoController(MedicalInfoService medicalInfoService, MedicalInfoDtoAssembler medicalInfoDtoAssembler) {
        this.medicalInfoService = medicalInfoService;
        this.medicalInfoDtoAssembler = medicalInfoDtoAssembler;
    }

    @GetMapping(value = "/{medicalInfoId}")
    public ResponseEntity<MedicalInfoDto> getMedicalInfo(@PathVariable Integer medicalInfoId) {
        MedicalInfoEntity entity = medicalInfoService.findById(medicalInfoId);
        MedicalInfoDto fatigueMonitoringDto = medicalInfoDtoAssembler.toModel(entity);
        return new ResponseEntity<>(fatigueMonitoringDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<MedicalInfoDto>> getAllMedicalInfos() {
        List<MedicalInfoEntity> entityList = medicalInfoService.findAll();
        CollectionModel<MedicalInfoDto> dtos = medicalInfoDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalInfoDto> addMedicalInfo(@RequestBody MedicalInfoEntity entity) {
        MedicalInfoEntity newEntity = medicalInfoService.create(entity);
        MedicalInfoDto dto = medicalInfoDtoAssembler.toModel(newEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{medicalInfoId}")
    public ResponseEntity<?> updateMedicalInfo(@RequestBody MedicalInfoEntity entity, @PathVariable Integer medicalInfoId) {
        medicalInfoService.update(medicalInfoId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{medicalInfoId}")
    public ResponseEntity<?> deleteMedicalInfo(@PathVariable Integer medicalInfoId) {
        medicalInfoService.delete(medicalInfoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
