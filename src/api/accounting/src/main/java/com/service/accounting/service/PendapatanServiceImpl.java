package com.service.accounting.service;

import com.service.accounting.exception.InputFormatException;
import com.service.accounting.exception.NotFoundException;
import com.service.accounting.model.Pendapatan;
import com.service.accounting.repository.PendapatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PendapatanServiceImpl implements PendapatanService {
    private PendapatanRepository repository;

    @Autowired
    public void setRepository(PendapatanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pendapatan newPendapatan(Pendapatan partialValue) {
        return null;
    }

    public Pendapatan getPendapatanById(int id) {
        return null;
    }

    @Override
    public Pendapatan updatePendapatan(int idPendapatan, Pendapatan partialValue) {
        return null;
    }

    @Override
    public List<Pendapatan> getPendapatan(Integer start, Integer limit) {
        return null;
    }

    @Override
    public Integer getNumberOfPendapatan() {
        return null;
    }

    @Override
    public List<Pendapatan> getPendapatanByPeriod(String tahun) {
        return null;
    }

    @Override
    public List<Pendapatan> getPendapatanByPeriod(String tahun, String bulan) {
        return null;
    }
}
