package ua.boretskyi.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.DriverController;
import ua.boretskyi.domain.DriverEntity;
import ua.boretskyi.dto.DriverDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DriverDtoAssembler implements RepresentationModelAssembler<DriverEntity, DriverDto> {
    @Override
    public DriverDto toModel(DriverEntity entity) {
        DriverDto driverDto = DriverDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .companyName(entity.getCompany().getTitle())
                .phoneNumber(entity.getPhoneNumber())
                .build();
        Link selfLink = linkTo(methodOn(DriverController.class).getDriver(driverDto.getId())).withSelfRel();
        driverDto.add(selfLink);
        return driverDto;
    }

    @Override
    public CollectionModel<DriverDto> toCollectionModel(Iterable<? extends DriverEntity> entities) {
        CollectionModel<DriverDto> driverDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DriverController.class).getAllDrivers()).withSelfRel();
        driverDtos.add(selfLink);
        return driverDtos;
    }
}
