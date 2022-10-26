package ua.boretskyi.controller;

import java.util.List;
import java.util.Optional;

public interface GeneralController<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    Optional<T> create(T entity);

    Optional<T> update(ID id, T entity);

    boolean delete(ID id);
}
