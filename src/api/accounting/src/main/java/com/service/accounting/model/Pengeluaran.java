package com.service.accounting.model;

import com.service.accounting.utils.InputValidator;

/**
 * POJO dari objek Pengeluaran
 */
public class Pengeluaran {
    private Integer idpengeluaran;
    private String tanggal;
    private String keterangan;
    private Long jumlah;

    public Integer getIdpengeluaran() {
        return idpengeluaran;
    }

    public void setIdpengeluaran(Integer idpengeluaran) {
        this.idpengeluaran = idpengeluaran;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        InputValidator.checkValidDate(tanggal);
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        InputValidator.checkValidKeterangan(keterangan);
        this.keterangan = keterangan;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }
}
