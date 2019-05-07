package com.service.accounting.model;

public class Pendapatan {
    private Integer idpendapatan;
    private String tanggal;
    private Long jumlah;

    public Integer getIdpendapatan() {
        return idpendapatan;
    }

    public void setIdpendapatan(Integer idpendapatan) {
        this.idpendapatan = idpendapatan;
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
