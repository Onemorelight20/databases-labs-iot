package ua.boretskyi.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.VehicleTypeController;
import ua.boretskyi.domain.VehicleTypeEntity;
import ua.boretskyi.dto.VehicleTypeDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VehicleTypeDtoAssembler implements RepresentationModelAssembler<VehicleTypeEntity, VehicleTypeDto> {
    @Override
    public VehicleTypeDto toModel(VehicleTypeEntity entity) {
        VehicleTypeDto vehicleTypeDto = VehicleTypeDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
        Link selfLink = linkTo(methodOn(VehicleTypeController.class).getVehicleType(vehicleTypeDto.getId())).withSelfRel();
        vehicleTypeDto.add(selfLink);
        return vehicleTypeDto;
    }

    @Override
    public CollectionModel<VehicleTypeDto> toCollectionModel(Iterable<? extends VehicleTypeEntity> entities) {
        CollectionModel<VehicleTypeDto> vehicleTypeDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(VehicleTypeController.class).getAllVehicleTypes()).withSelfRel();
        vehicleTypeDtos.add(selfLink);
        return vehicleTypeDtos;
    }
}
