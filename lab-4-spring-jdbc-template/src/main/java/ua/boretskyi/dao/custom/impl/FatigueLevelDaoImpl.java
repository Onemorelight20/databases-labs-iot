package ua.boretskyi.dao.custom.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.boretskyi.dao.custom.FatigueLevelDao;
import ua.boretskyi.model.FatigueLevel;

import java.util.List;
import java.util.Optional;

@Repository
public class FatigueLevelDaoImpl implements FatigueLevelDao {
    public static final String FIND_ALL = "SELECT * FROM fatigue_level";
    public static final String FIND_BY_TITLE = "SELECT * FROM fatigue_level WHERE level_title=?";
    public static final String CREATE = "INSERT fatigue_level(level_title) VALUES (?)";
    public static final String UPDATE = "UPDATE fatigue_level SET level_title=? WHERE level_title=?";
    public static final String DELETE = "DELETE FROM fatigue_level WHERE level_title=?";

    private final JdbcTemplate jdbcTemplate;

    public FatigueLevelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<FatigueLevel> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(FatigueLevel.class));
    }

    @Override
    public Optional<FatigueLevel> findById(String id) {
        Optional<FatigueLevel> fatigueLevel;
        try {
            fatigueLevel = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_TITLE, BeanPropertyRowMapper.newInstance(FatigueLevel.class), id));
        } catch (EmptyResultDataAccessException e) {
            fatigueLevel = Optional.empty();
        }
        return fatigueLevel;
    }

    @Override
    public Optional<FatigueLevel> create(FatigueLevel entity) {
        jdbcTemplate.update(CREATE, entity.getLevelTitle());
        return findById(entity.getLevelTitle());
    }

    @Override
    public Optional<FatigueLevel> update(String id, FatigueLevel entity) {
        jdbcTemplate.update(UPDATE, entity.getLevelTitle());
        return findById(entity.getLevelTitle());
    }

    @Override
    public boolean delete(String id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public Optional<FatigueLevel> findByTitle(String title) {
        return findById(title);
    }
}
