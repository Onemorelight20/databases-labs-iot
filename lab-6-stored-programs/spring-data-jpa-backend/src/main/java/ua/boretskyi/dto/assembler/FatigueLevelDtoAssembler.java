package ua.boretskyi.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.FatigueLevelController;
import ua.boretskyi.domain.FatigueLevelEntity;
import ua.boretskyi.dto.FatigueLevelDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FatigueLevelDtoAssembler implements RepresentationModelAssembler<FatigueLevelEntity, FatigueLevelDto> {
    @Override
    public FatigueLevelDto toModel(FatigueLevelEntity entity) {
        FatigueLevelDto fatigueLevelDto = FatigueLevelDto.builder()
                .levelTitle(entity.getLevelTitle())
                .build();
        Link selfLink = linkTo(methodOn(FatigueLevelController.class).getFatigueLevel(fatigueLevelDto.getLevelTitle())).withSelfRel();
        fatigueLevelDto.add(selfLink);
        return fatigueLevelDto;
    }

    @Override
    public CollectionModel<FatigueLevelDto> toCollectionModel(Iterable<? extends FatigueLevelEntity> entities) {
        CollectionModel<FatigueLevelDto> fatigueLevelDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(FatigueLevelController.class).getAllFatigueLevels()).withSelfRel();
        fatigueLevelDtos.add(selfLink);
        return fatigueLevelDtos;
    }
}
