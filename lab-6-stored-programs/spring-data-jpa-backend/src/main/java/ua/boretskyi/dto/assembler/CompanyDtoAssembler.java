package ua.boretskyi.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.boretskyi.controller.CompanyController;
import ua.boretskyi.domain.CompanyEntity;
import ua.boretskyi.dto.CompanyDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CompanyDtoAssembler implements RepresentationModelAssembler<CompanyEntity, CompanyDto> {
    @Override
    public CompanyDto toModel(CompanyEntity entity) {
        CompanyDto companyDto = CompanyDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();
        Link selfLink = linkTo(methodOn(CompanyController.class).getCompany(companyDto.getId())).withSelfRel();
        companyDto.add(selfLink);
        return companyDto;
    }

    @Override
    public CollectionModel<CompanyDto> toCollectionModel(Iterable<? extends CompanyEntity> entities) {
        CollectionModel<CompanyDto> companyDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CompanyController.class).getAllCompanies()).withSelfRel();
        companyDtos.add(selfLink);
        return companyDtos;
    }
}
