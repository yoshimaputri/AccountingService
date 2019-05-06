package com.service.accounting.service;

import com.service.accounting.model.Pendapatan;

public interface PendapatanService {
    Pendapatan newPendapatan(String tanggal, long jumlah);
}
