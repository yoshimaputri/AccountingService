package com.service.accounting.service;

import com.service.accounting.model.Pendapatan;

import java.util.List;

public interface PendapatanService {
    Pendapatan newPendapatan(String tanggal, long jumlah);

    Pendapatan getPendapatanById(int idpendapatan);

    void changeTanggal(int idpendapatan, String tanggal);

    void changeJumlah(int idpendapatan, long jumlah);

    List<Pendapatan> getPendapatan();

    List<Pendapatan> getPendapatan(int tahun);

    List<Pendapatan> getPendapatan(int tahun, int bulan);
}
