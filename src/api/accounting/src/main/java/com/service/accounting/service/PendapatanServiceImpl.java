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
        return repository.save(partialValue);
    }

    public Pendapatan getPendapatanById(int id) {
        return repository.get(id);
    }

    @Override
    public Pendapatan updatePendapatan(int idPendapatan, Pendapatan partialValue) {
        Pendapatan pendapatan = repository.get(idPendapatan);
        if (pendapatan != null) {
            if (partialValue.getTanggal() != null) {
                pendapatan.setTanggal(partialValue.getTanggal());
            }
            if (partialValue.getJumlah() != null) {
                pendapatan.setJumlah(partialValue.getJumlah());
            }

            return repository.update(pendapatan);
        }
        else {
            throw new NotFoundException("Pendapatan", idPendapatan);
        }
    }

    @Override
    public List<Pendapatan> getPendapatan(Integer start, Integer limit) {
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
    public Integer getNumberOfPendapatan() {
        return repository.count();
    }

    @Override
    public List<Pendapatan> getPendapatanByPeriod(String tahun) {
        return repository.getByPeriod(tahun);
    }

    @Override
    public List<Pendapatan> getPendapatanByPeriod(String tahun, String bulan) {
        return repository.getByPeriod(tahun, bulan);
    }
}
