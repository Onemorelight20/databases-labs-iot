package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.MineSightDao;
import ua.boretskyi.model.MineSight;

import java.util.List;
import java.util.Optional;

@Repository
public class MineSightDaoImpl implements MineSightDao {

    public static final String FIND_ALL = "SELECT * FROM mine_sight";
    public static final String FIND_BY_ID = "SELECT * FROM mine_sight WHERE id=?";
    public static final String FIND_BY_TITLE = "SELECT * FROM mine_sight WHERE title=?";
    public static final String FIND_BY_COMPANY_ID = "SELECT * FROM mine_sight " +
            "WHERE EXISTS(SELECT * FROM company_mine_sight WHERE mine_sight_id=id and company_id=?)";
    public static final String CREATE = "INSERT mine_sight(country, city, title, area_in_square_meters) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE mine_sight SET country=?, city=?, title=?, area_in_square_meters=? WHERE id=?";
    public static final String DELETE = "DELETE FROM mine_sight WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public MineSightDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MineSight> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(MineSight.class));
    }

    @Override
    public Optional<MineSight> findById(Integer id) {
        Optional<MineSight> mineSight;
        try {
            mineSight = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(MineSight.class), id));
        } catch (EmptyResultDataAccessException e) {
            mineSight = Optional.empty();
        }
        return mineSight;
    }

    @Override
    public Optional<MineSight> findByTitle(String title) {
        Optional<MineSight> mineSight;
        try {
            mineSight = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_TITLE, BeanPropertyRowMapper.newInstance(MineSight.class), title));
        } catch (EmptyResultDataAccessException e) {
            mineSight = Optional.empty();
        }
        return mineSight;
    }

    @Override
    public Optional<MineSight> create(MineSight entity) {
        jdbcTemplate.update(CREATE, entity.getCountry(), entity.getCity(), entity.getTitle(), entity.getAreaInSquareMeters());
        return this.findByTitle(entity.getTitle());
    }

    @Override
    public Optional<MineSight> update(Integer id, MineSight entity) {
        jdbcTemplate.update(UPDATE, entity.getCountry(), entity.getCity(), entity.getTitle(), entity.getAreaInSquareMeters(), id);
        return this.findById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public List<MineSight> getMineSightsOfCompanyWithId(Integer companyId) {
        return jdbcTemplate.query(FIND_BY_COMPANY_ID, BeanPropertyRowMapper.newInstance(MineSight.class), companyId);
    }


}
