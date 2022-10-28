package ua.boretskyi.service.impl;

import org.springframework.stereotype.Service;
import ua.boretskyi.domain.DriverEntity;
import ua.boretskyi.exception.EntityNotFoundException;
import ua.boretskyi.repository.DriverRepository;
import ua.boretskyi.service.DriverService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    @Override
    public List<DriverEntity> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public DriverEntity findById(Integer id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver", String.valueOf(id)));
    }

    @Override
    @Transactional
    public DriverEntity create(DriverEntity entity) {
        driverRepository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public void update(Integer id, DriverEntity entity) {
        DriverEntity driverEntity = findById(id);
        driverEntity.setName(entity.getName());
        driverEntity.setSurname(entity.getSurname());
        driverEntity.setCompany(entity.getCompany());
        driverRepository.save(driverEntity);
    }

    @Override
    public void delete(Integer id) {
        DriverEntity driverEntity = findById(id);
        driverRepository.delete(driverEntity);
    }
}
