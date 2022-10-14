package ua.boretskyi.dao;

import java.util.List;
import java.util.Optional;

public interface GeneralDao<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    T create(T entity);

    T update(ID id, T entity);

    boolean delete(ID id);
}
