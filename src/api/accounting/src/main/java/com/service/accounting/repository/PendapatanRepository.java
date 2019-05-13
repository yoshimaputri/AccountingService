package com.service.accounting.repository;

import com.service.accounting.model.Pendapatan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendapatanRepository extends CrudRepository<Pendapatan, Integer> {
    List<Pendapatan> findAllByTanggal(String tanggal);
}
