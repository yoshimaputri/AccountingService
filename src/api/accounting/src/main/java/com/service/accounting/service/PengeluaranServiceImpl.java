package com.service.accounting.service;

import com.service.accounting.exception.InputFormatException;
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
    public Pengeluaran newPengeluaran(Pengeluaran partialValue) {
        return repository.save(partialValue);
    }

    @Override
    public Pengeluaran getPengeluaranById(int id) {
        return repository.get(id);
    }

    @Override
    public Pengeluaran updatePengeluaran(int idPengeluaran, Pengeluaran partialValue) {
        Pengeluaran pengeluaran = repository.get(idPengeluaran);
        if (pengeluaran != null) {
            if (partialValue.getTanggal() != null) {
                pengeluaran.setTanggal(partialValue.getTanggal());
            }
            if (partialValue.getKeterangan() != null) {
                pengeluaran.setKeterangan(partialValue.getKeterangan());
            }
            if (partialValue.getJumlah() != null) {
                pengeluaran.setJumlah(partialValue.getJumlah());
            }

            return repository.update(pengeluaran);
        } else {
            throw new NotFoundException("Pengeluaran", idPengeluaran);
        }
    }

    @Override
    public List<Pengeluaran> getPengeluaran(Integer start, Integer limit) {
        if (start != null) {
            start--;
            if (start < 0) {
                throw new InputFormatException("Start value must greater than 0.");
            }
            if (limit != null) {
                if (limit < 1) {
                    throw new InputFormatException("Limit value must greater than 0.");
                }
                return repository.get(start, limit);
            } else {
                return repository.get(start, 30);
            }
        } else {
            if (limit != null) {
                if (limit < 1) {
                    throw new InputFormatException("Limit value must greater than 0.");
                }
                return repository.get(0, limit);
            } else {
                return repository.get(0, 300);
            }
        }
    }

    @Override
    public Integer getNumberOfPengeluaran() {
        return repository.count();
    }

    @Override
    public List<Pengeluaran> getPengeluaranByPeriod(String tahun) {
        return repository.getByPeriod(tahun);
    }

    @Override
    public List<Pengeluaran> getPengeluaranByPeriod(String tahun, String bulan) {
        return repository.getByPeriod(tahun, bulan);
    }
}
