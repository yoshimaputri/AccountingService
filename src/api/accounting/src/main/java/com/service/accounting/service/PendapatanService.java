package com.service.accounting.service;

import com.service.accounting.model.Pendapatan;

import java.util.List;

public interface PendapatanService {
    Pendapatan newPendapatan(Pendapatan pendapatan);

    Pendapatan updatePendapatan(Pendapatan pendapatan);

    Pendapatan getPendapatanById(int id);

    List<Pendapatan> getPendapatan(String idRestaurant, Integer start, Integer limit);

    Long getNumberOfPendapatan();

    List<Pendapatan> getPendapatanByPeriod(String tahun);

    List<Pendapatan> getPendapatanByPeriod(String tahun, String bulan);
}
