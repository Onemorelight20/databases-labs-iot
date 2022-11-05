package ua.boretskyi.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.VehicleController;
import ua.boretskyi.controller.VehicleTypeController;
import ua.boretskyi.domain.VehicleEntity;
import ua.boretskyi.dto.VehicleDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VehicleDtoAssembler implements RepresentationModelAssembler<VehicleEntity, VehicleDto> {
    @Override
    public VehicleDto toModel(VehicleEntity entity) {
        VehicleDto vehicleDto = VehicleDto.builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .manufacturingDate(entity.getManufacturingDate())
                .vehicleType(entity.getVehicleType().getType())
                .serialNumber(entity.getSerialNumber())
                .build();

        vehicleDto.add(
                linkTo(methodOn(VehicleController.class).getVehicle(vehicleDto.getId())).withSelfRel(),
                linkTo(methodOn(VehicleTypeController.class).getVehicleType(entity.getVehicleType().getId())).withRel("vehicleType")
        );
        return vehicleDto;
    }

    @Override
    public CollectionModel<VehicleDto> toCollectionModel(Iterable<? extends VehicleEntity> entities) {
        CollectionModel<VehicleDto> vehicleDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(VehicleController.class).getAllVehicles()).withSelfRel();
        vehicleDtos.add(selfLink);
        return vehicleDtos;
    }
}
