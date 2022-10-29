package ua.boretskyi.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.MineSightController;
import ua.boretskyi.domain.MedicalInfoEntity;
import ua.boretskyi.domain.MineSightEntity;
import ua.boretskyi.dto.MedicalInfoDto;
import ua.boretskyi.dto.MineSightDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MineSightDtoAssembler  implements RepresentationModelAssembler<MineSightEntity, MineSightDto> {

    @Override
    public MineSightDto toModel(MineSightEntity entity) {
        MineSightDto mineSightDto = MineSightDto.builder()
                .id(entity.getId())
                .city(entity.getCity())
                .country(entity.getCountry())
                .title(entity.getTitle())
                .build();
        mineSightDto.add(
                linkTo(methodOn(MineSightController.class).getMineSight(mineSightDto.getId())).withSelfRel()
        );
        return mineSightDto;
    }

    @Override
    public CollectionModel<MineSightDto> toCollectionModel(Iterable<? extends MineSightEntity> entities) {
        CollectionModel<MineSightDto> mineSightDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(MineSightController.class).getAllMineSights()).withSelfRel();
        mineSightDtos.add(selfLink);
        return mineSightDtos;
    }
}
