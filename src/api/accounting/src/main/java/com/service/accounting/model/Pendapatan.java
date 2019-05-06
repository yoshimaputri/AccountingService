package com.service.accounting.model;

public class Pendapatan {
    private int idpendapatan;
    private String tanggal;
    private long jumlah;

    public int getIdpendapatan() {
        return idpendapatan;
    }

    public void setIdpendapatan(int idpendapatan) {
        this.idpendapatan = idpendapatan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public long getJumlah() {
        return jumlah;
    }

    public void setJumlah(long jumlah) {
        this.jumlah = jumlah;
    }
}
