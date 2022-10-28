package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.CompanyEntity;
import ua.boretskyi.dto.CompanyDto;
import ua.boretskyi.dto.assembler.CompanyDtoAssembler;
import ua.boretskyi.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyDtoAssembler companyDtoAssembler;

    public CompanyController(CompanyService companyService, CompanyDtoAssembler companyDtoAssembler) {
        this.companyService = companyService;
        this.companyDtoAssembler = companyDtoAssembler;
    }

    @GetMapping(value = "/{companyId}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Integer companyId) {
        CompanyEntity companyEntity = companyService.findById(companyId);
        CompanyDto companyDto = companyDtoAssembler.toModel(companyEntity);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<CompanyDto>> getAllCompanies() {
        List<CompanyEntity> companyEntityList = companyService.findAll();
        CollectionModel<CompanyDto> companyDtos = companyDtoAssembler.toCollectionModel(companyEntityList);
        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyEntity companyEntity) {
        CompanyEntity newCompanyEntity = companyService.create(companyEntity);
        CompanyDto companyDto = companyDtoAssembler.toModel(newCompanyEntity);
        return new ResponseEntity<>(companyDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{companyId}")
    public ResponseEntity<?> updateCompany(@RequestBody CompanyEntity companyEntity, @PathVariable Integer companyId) {
        companyService.update(companyId, companyEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Integer companyId) {
        companyService.delete(companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
