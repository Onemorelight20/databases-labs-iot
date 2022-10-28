package ua.boretskyi.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.MedicalInfoController;
import ua.boretskyi.domain.MedicalInfoEntity;
import ua.boretskyi.dto.MedicalInfoDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MedicalInfoDtoAssembler implements RepresentationModelAssembler<MedicalInfoEntity, MedicalInfoDto> {
    @Override
    public MedicalInfoDto toModel(MedicalInfoEntity entity) {
        MedicalInfoDto medicalInfoDto = MedicalInfoDto.builder()
                .id(entity.getId())
                .bloodType(entity.getBloodType())
                .driverPhoneNumber(entity.getDriver().getPhoneNumber())
                .sightState(entity.getSightState())
                .updatedAt(entity.getUpdatedAt())
                .build();
        Link selfLink = linkTo(methodOn(MedicalInfoController.class).getMedicalInfo(medicalInfoDto.getId())).withSelfRel();
        medicalInfoDto.add(selfLink);
        return medicalInfoDto;
    }

    @Override
    public CollectionModel<MedicalInfoDto> toCollectionModel(Iterable<? extends MedicalInfoEntity> entities) {
        CollectionModel<MedicalInfoDto> medicalInfoDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(MedicalInfoController.class).getAllMedicalInfos()).withSelfRel();
        medicalInfoDtos.add(selfLink);
        return medicalInfoDtos;
    }
}
