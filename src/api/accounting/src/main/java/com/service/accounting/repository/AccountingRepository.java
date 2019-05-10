package com.service.accounting.repository;

import java.util.List;

public interface AccountingRepository<T> {
    T save(T data);

    T update(T data);

    T get(Integer id);

    List<T> get(Integer start, Integer limit);

    List<T> getByPeriod(String year);

    List<T> getByPeriod(String year, String month);

    Integer count();
}
