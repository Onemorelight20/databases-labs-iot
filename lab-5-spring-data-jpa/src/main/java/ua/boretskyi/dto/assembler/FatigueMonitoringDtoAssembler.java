package ua.boretskyi.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.DriverController;
import ua.boretskyi.controller.FatigueMonitoringController;
import ua.boretskyi.controller.MineSightController;
import ua.boretskyi.controller.VehicleController;
import ua.boretskyi.domain.FatigueMonitoringEntity;
import ua.boretskyi.dto.FatigueMonitoringDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FatigueMonitoringDtoAssembler implements RepresentationModelAssembler<FatigueMonitoringEntity, FatigueMonitoringDto> {
    @Override
    public FatigueMonitoringDto toModel(FatigueMonitoringEntity entity) {
        FatigueMonitoringDto fatigueMonitoringDto = FatigueMonitoringDto.builder()
                .id(entity.getId())
                .driverId(entity.getDriverId())
                .fatigueLevelTitle(entity.getFatigueLevelTitle())
                .mineSightId(entity.getMineSightId())
                .vehicleId(entity.getVehicleId())
                .recordTime(entity.getRecordTime())
                .build();

        fatigueMonitoringDto.add(
                linkTo(methodOn(FatigueMonitoringController.class).getFatigueMonitoring(fatigueMonitoringDto.getId())).withSelfRel(),
                linkTo(methodOn(DriverController.class).getDriver(fatigueMonitoringDto.getDriverId())).withRel("driver"),
                linkTo(methodOn(MineSightController.class).getMineSight(fatigueMonitoringDto.getMineSightId())).withRel("mineSight"),
                linkTo(methodOn(VehicleController.class).getVehicle(fatigueMonitoringDto.getVehicleId())).withRel("vehicle")
        );

        return fatigueMonitoringDto;
    }

    @Override
    public CollectionModel<FatigueMonitoringDto> toCollectionModel(Iterable<? extends FatigueMonitoringEntity> entities) {
        CollectionModel<FatigueMonitoringDto> fatigueMonitoringDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(FatigueMonitoringController.class).getAllFatigueMonitorings()).withSelfRel();
        fatigueMonitoringDtos.add(selfLink);
        return fatigueMonitoringDtos;
    }
}
