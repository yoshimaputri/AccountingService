package com.service.accounting.repository;

import com.service.accounting.model.Pendapatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendapatanRepository extends JpaRepository<Pendapatan, Integer> {
    List<Pendapatan> findAllByIdRestaurant(String idRestaurant);
}
