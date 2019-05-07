package com.service.accounting.model;

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
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }
}
