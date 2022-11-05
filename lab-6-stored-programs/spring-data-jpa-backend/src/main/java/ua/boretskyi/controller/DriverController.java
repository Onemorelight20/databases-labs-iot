package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.DriverEntity;
import ua.boretskyi.dto.DriverDto;
import ua.boretskyi.dto.assembler.DriverDtoAssembler;
import ua.boretskyi.service.DriverService;

import java.util.List;

@RestController
@RequestMapping(value = "api/drivers")
public class DriverController {

    private final DriverService driverService;
    private final DriverDtoAssembler driverDtoAssembler;

    public DriverController(DriverService driverService, DriverDtoAssembler driverDtoAssembler) {
        this.driverService = driverService;
        this.driverDtoAssembler = driverDtoAssembler;
    }

    @GetMapping(value = "/{driverId}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable Integer driverId) {
        DriverEntity driverEntity = driverService.findById(driverId);
        DriverDto driverDto = driverDtoAssembler.toModel(driverEntity);
        return new ResponseEntity<>(driverDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<DriverDto>> getAllDrivers() {
        List<DriverEntity> driverEntityList = driverService.findAll();
        CollectionModel<DriverDto> driverDtos = driverDtoAssembler.toCollectionModel(driverEntityList);
        return new ResponseEntity<>(driverDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DriverDto> addDriver(@RequestBody DriverEntity driverEntity) {
        DriverEntity newDriverEntity = driverService.create(driverEntity);
        DriverDto driverDto = driverDtoAssembler.toModel(newDriverEntity);
        return new ResponseEntity<>(driverDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{driverId}")
    public ResponseEntity<?> updateDriver(@RequestBody DriverEntity driverEntity, @PathVariable Integer driverId) {
        driverService.update(driverId, driverEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer driverId) {
        driverService.delete(driverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
