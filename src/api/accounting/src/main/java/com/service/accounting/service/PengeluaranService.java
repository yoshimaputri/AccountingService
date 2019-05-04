package com.service.accounting.service;

import com.service.accounting.model.Pengeluaran;

/**
 * Interface untuk Service dari Pengeluaran
 */
public interface PengeluaranService {
    Pengeluaran newPengeluaran(String tanggal, String keterangan, long jumlah);

    // TODO: add more method here
}
