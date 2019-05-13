package com.service.accounting.service;

import com.service.accounting.exception.NotFoundException;
import com.service.accounting.model.Pengeluaran;
import com.service.accounting.repository.PengeluaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementasi dari interface PengeluaranService
 */
@Service
public class PengeluaranServiceImpl implements PengeluaranService {
    private PengeluaranRepository repository;

    @Autowired
    public void setRepository(PengeluaranRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pengeluaran newPengeluaran(Pengeluaran pengeluaran) {
        return repository.save(pengeluaran);
    }

    @Override
    public Pengeluaran getPengeluaranById(int id) {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("peng_id", id));
    }

    @Override
    public Pengeluaran updatePengeluaran(Pengeluaran pengeluaran) {
        return null;
    }

    @Override
    public List<Pengeluaran> getPengeluaran(Integer start, Integer limit) {
        return null;
    }

    @Override
    public Integer getNumberOfPengeluaran() {
        return null;
    }

    @Override
    public List<Pengeluaran> getPengeluaranByPeriod(String tahun) {
        return null;
    }

    @Override
    public List<Pengeluaran> getPengeluaranByPeriod(String tahun, String bulan) {
        return null;
    }
}
