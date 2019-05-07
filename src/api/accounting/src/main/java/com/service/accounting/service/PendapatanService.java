package com.service.accounting.service;

import com.service.accounting.model.Pendapatan;

import java.util.List;

public interface PendapatanService {
    Pendapatan newPendapatan(String tanggal, long jumlah);

    Pendapatan getPendapatanById(int idpendapatan);

    void changeTanggal(int idpendapatan, String tanggal);

    void changeJumlah(int idpendapatan, long jumlah);

    List<Pendapatan> getPendapatan(Integer start, Integer limit);

    Integer getNumberOfPendapatan();

    List<Pendapatan> getPendapatanByPeriod(int tahun);

    List<Pendapatan> getPendapatanByPeriod(int tahun, int bulan);
}
