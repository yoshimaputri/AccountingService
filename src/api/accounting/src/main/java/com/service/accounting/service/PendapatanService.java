package com.service.accounting.service;

import com.service.accounting.model.Pendapatan;

import java.util.List;

public interface PendapatanService {
    Pendapatan newPendapatan(Pendapatan partialValue);

    Pendapatan updatePendapatan(Pendapatan partialValue);

    List<Pendapatan> getPendapatan(Integer start, Integer limit);

    Integer getNumberOfPendapatan();

    List<Pendapatan> getPendapatanByPeriod(String tahun);

    List<Pendapatan> getPendapatanByPeriod(String tahun, String bulan);
}
