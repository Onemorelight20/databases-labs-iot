package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.VehicleTypeEntity;
import ua.boretskyi.dto.VehicleTypeDto;
import ua.boretskyi.dto.assembler.VehicleTypeDtoAssembler;
import ua.boretskyi.service.VehicleTypeService;

import java.util.List;

@RestController
@RequestMapping(value = "api/vehicleTypes")
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;
    private final VehicleTypeDtoAssembler vehicleTypeDtoAssembler;

    public VehicleTypeController(VehicleTypeService vehicleTypeService, VehicleTypeDtoAssembler vehicleTypeDtoAssembler) {
        this.vehicleTypeService = vehicleTypeService;
        this.vehicleTypeDtoAssembler = vehicleTypeDtoAssembler;
    }

    @GetMapping(value = "/{vehicleTypeId}")
    public ResponseEntity<VehicleTypeDto> getVehicleType(@PathVariable Integer vehicleTypeId) {
        VehicleTypeEntity vehicleTypeEntity = vehicleTypeService.findById(vehicleTypeId);
        VehicleTypeDto vehicleTypeDto = vehicleTypeDtoAssembler.toModel(vehicleTypeEntity);
        return new ResponseEntity<>(vehicleTypeDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<VehicleTypeDto>> getAllVehicleTypes() {
        List<VehicleTypeEntity> entityList = vehicleTypeService.findAll();
        CollectionModel<VehicleTypeDto> dtos = vehicleTypeDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehicleTypeDto> addVehicleType(@RequestBody VehicleTypeEntity entity) {
        VehicleTypeEntity newEntity = vehicleTypeService.create(entity);
        VehicleTypeDto dto = vehicleTypeDtoAssembler.toModel(newEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{vehicleTypeId}")
    public ResponseEntity<?> updateVehicleType(@RequestBody VehicleTypeEntity entity, @PathVariable Integer vehicleTypeId) {
        vehicleTypeService.update(vehicleTypeId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{vehicleTypeId}")
    public ResponseEntity<?> deleteVehicleType(@PathVariable Integer vehicleTypeId) {
        vehicleTypeService.delete(vehicleTypeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
