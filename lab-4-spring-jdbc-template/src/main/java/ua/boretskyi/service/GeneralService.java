package ua.boretskyi.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    Optional<T> create(T entity);

    Optional<T> update(ID id, T entity);

    boolean delete(ID id);
}
