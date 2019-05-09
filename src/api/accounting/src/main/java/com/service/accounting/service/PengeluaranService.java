package com.service.accounting.service;

import com.service.accounting.model.Pengeluaran;

import java.util.List;

/**
 * Interface untuk Service dari Pengeluaran
 */
public interface PengeluaranService {
    Pengeluaran newPengeluaran(Pengeluaran partialValue);

    Pengeluaran updatePengeluaran(Pengeluaran partialValue);

    List<Pengeluaran> getPengeluaran(Integer start, Integer limit);

    Integer getNumberOfPengeluaran();

    List<Pengeluaran> getPengeluaranByPeriod(String tahun);

    List<Pengeluaran> getPengeluaranByPeriod(String tahun, String bulan);
}
