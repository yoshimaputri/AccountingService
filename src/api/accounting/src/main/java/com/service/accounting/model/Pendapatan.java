package com.service.accounting.model;

public class Pendapatan {
    private Integer idPendapatan;
    private String tanggal;
    private Long jumlah;

    public Integer getIdPendapatan() {
        return idPendapatan;
    }

    public void setIdPendapatan(Integer idPendapatan) {
        this.idPendapatan = idPendapatan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }
}
