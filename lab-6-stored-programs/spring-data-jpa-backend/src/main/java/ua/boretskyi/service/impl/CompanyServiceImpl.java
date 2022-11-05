package ua.boretskyi.service.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.domain.CompanyEntity;
import ua.boretskyi.exception.EntityNotFoundException;
import ua.boretskyi.repository.CompanyRepository;
import ua.boretskyi.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyEntity> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyEntity findById(Integer id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company", String.valueOf(id)));
    }

    @Override
    @Transactional
    public CompanyEntity create(CompanyEntity entity) {
        companyRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, CompanyEntity entity) {
        CompanyEntity companyEntity = findById(id);
        companyEntity.setTitle(entity.getTitle());
        companyRepository.save(companyEntity);
    }

    @Override
    public void delete(Integer id) {
        CompanyEntity companyEntity = findById(id);
        companyRepository.delete(companyEntity);
    }
}
