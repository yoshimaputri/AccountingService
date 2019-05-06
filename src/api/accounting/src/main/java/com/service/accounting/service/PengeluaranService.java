package com.service.accounting.service;

import com.service.accounting.model.Pengeluaran;

import java.util.List;

/**
 * Interface untuk Service dari Pengeluaran
 */
public interface PengeluaranService {
    Pengeluaran newPengeluaran(String tanggal, String keterangan, long jumlah);

    Pengeluaran getPengeluaranById(int idpengeluaran);

    void changeTanggal(int idpengeluaran, String tanggal);

    void changeKeterangan(int idpengeluaran, String keterangan);

    void changeJumlah(int idpengeluaran, long jumlah);

    List<Pengeluaran> getPengeluaran();

    List<Pengeluaran> getPengeluaran(int tahun);

    List<Pengeluaran> getPengeluaran(int tahun, int bulan);
}
