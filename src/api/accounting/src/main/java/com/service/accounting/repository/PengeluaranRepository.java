package com.service.accounting.repository;

import com.service.accounting.model.Pengeluaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PengeluaranRepository extends JpaRepository<Pengeluaran, Integer> {
    List<Pengeluaran> findAllByIdRestaurant(String idRestaurant);
}
