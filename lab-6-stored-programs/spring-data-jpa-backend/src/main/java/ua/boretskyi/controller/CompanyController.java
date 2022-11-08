package ua.boretskyi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.boretskyi.domain.CompanyEntity;
import ua.boretskyi.domain.MineSightEntity;
import ua.boretskyi.dto.CompanyDto;
import ua.boretskyi.dto.MineSightDto;
import ua.boretskyi.dto.assembler.CompanyDtoAssembler;
import ua.boretskyi.dto.assembler.MineSightDtoAssembler;
import ua.boretskyi.service.CompanyService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyDtoAssembler companyDtoAssembler;
    private final MineSightDtoAssembler mineSightDtoAssembler;

    public CompanyController(CompanyService companyService, CompanyDtoAssembler companyDtoAssembler, MineSightDtoAssembler mineSightDtoAssembler) {
        this.companyService = companyService;
        this.companyDtoAssembler = companyDtoAssembler;
        this.mineSightDtoAssembler = mineSightDtoAssembler;
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

    @GetMapping(value = "/getAllMineSightsForCompanyWithId/{companyId}")
    public ResponseEntity<CollectionModel<MineSightDto>> getAllMineSightsForCompanyWithId(@PathVariable Integer companyId) {
        CompanyEntity companyEntity = companyService.findById(companyId);
        Set<MineSightEntity> mineSightEntityList = companyEntity.getMineSightEntities();
        CollectionModel<MineSightDto> mineSightDtos = mineSightDtoAssembler.toCollectionModel(mineSightEntityList);
        return new ResponseEntity<>(mineSightDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insertTenCompanies")
    public ResponseEntity<?> insertTenCompanies() {
        companyService.insertTenCompanies();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getIdsSum")
    public ResponseEntity<Integer> getIdsSum() {
        return new ResponseEntity<>(companyService.getCompaniesIdSum(), HttpStatus.OK);
    }

    @PostMapping(value = "/createTwoTablesAndInsertDataDynamically")
    public ResponseEntity<?> createTwoTablesAndInsertDataDynamically() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
