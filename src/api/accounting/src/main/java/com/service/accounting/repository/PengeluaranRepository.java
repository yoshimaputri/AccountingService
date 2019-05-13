package com.service.accounting.repository;

import com.service.accounting.model.Pengeluaran;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengeluaranRepository extends CrudRepository<Pengeluaran, Integer> {
}
