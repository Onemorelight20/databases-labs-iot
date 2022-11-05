package ua.boretskyi.service;

import java.util.List;

public interface GeneralService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T create(T entity);

    void update(ID id, T entity);

    void delete(ID id);
}
