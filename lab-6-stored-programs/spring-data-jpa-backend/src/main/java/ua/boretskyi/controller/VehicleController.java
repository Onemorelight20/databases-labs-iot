package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.VehicleEntity;
import ua.boretskyi.dto.VehicleDto;
import ua.boretskyi.dto.assembler.VehicleDtoAssembler;
import ua.boretskyi.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping(value = "api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final VehicleDtoAssembler vehicleDtoAssembler;

    public VehicleController(VehicleService vehicleService, VehicleDtoAssembler vehicleDtoAssembler) {
        this.vehicleService = vehicleService;
        this.vehicleDtoAssembler = vehicleDtoAssembler;
    }

    @GetMapping(value = "/{vehicleId}")
    public ResponseEntity<VehicleDto> getVehicle(@PathVariable Integer vehicleId) {
        VehicleEntity vehicleEntity = vehicleService.findById(vehicleId);
        VehicleDto fatigueMonitoringDto = vehicleDtoAssembler.toModel(vehicleEntity);
        return new ResponseEntity<>(fatigueMonitoringDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<VehicleDto>> getAllVehicles() {
        List<VehicleEntity> entityList = vehicleService.findAll();
        CollectionModel<VehicleDto> dtos = vehicleDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehicleDto> addVehicle(@RequestBody VehicleEntity entity) {
        VehicleEntity newEntity = vehicleService.create(entity);
        VehicleDto dto = vehicleDtoAssembler.toModel(newEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleEntity entity, @PathVariable Integer vehicleId) {
        vehicleService.update(vehicleId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Integer vehicleId) {
        vehicleService.delete(vehicleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/withVehicleTypeId/{vehicleTypeId}")
    public ResponseEntity<CollectionModel<VehicleDto>> getAllWithVehicleTypeId(@PathVariable Integer vehicleTypeId) {
        List<VehicleEntity> entityList = vehicleService.getVehicleEntitiesByVehicleTypeId(vehicleTypeId);
        CollectionModel<VehicleDto> dtos = vehicleDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
