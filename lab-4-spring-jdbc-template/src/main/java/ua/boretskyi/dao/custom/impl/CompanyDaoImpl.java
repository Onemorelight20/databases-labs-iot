package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.CompanyDao;
import ua.boretskyi.model.Company;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    public static final String FIND_ALL = "SELECT * FROM company";
    public static final String FIND_BY_ID = "SELECT * FROM company WHERE id=?";
    public static final String FIND_BY_TITLE = "SELECT * FROM company WHERE title=?";
    public static final String CREATE = "INSERT company(title) VALUES (?)";
    public static final String UPDATE = "UPDATE company SET title=? WHERE id=?";
    public static final String DELETE = "DELETE FROM company WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public CompanyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Company> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Company.class));
    }

    @Override
    public Optional<Company> findById(Integer id) {
        Optional<Company> company;
        try {
            company = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(Company.class), id));
        } catch (EmptyResultDataAccessException e) {
            company = Optional.empty();
        }
        return company;
    }

    @Override
    public Optional<Company> findByTitle(String title) {
        Optional<Company> company;
        try {
            company = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_TITLE, BeanPropertyRowMapper.newInstance(Company.class), title));
        } catch (EmptyResultDataAccessException e) {
            company = Optional.empty();
        }
        return company;
    }

    @Override
    public Optional<Company> create(Company entity) {
        jdbcTemplate.update(CREATE, entity.getTitle());
        return findByTitle(entity.getTitle());
    }

    @Override
    public Optional<Company> update(Integer id, Company entity) {
        jdbcTemplate.update(UPDATE, entity.getTitle(), id);
        return findById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }
}
