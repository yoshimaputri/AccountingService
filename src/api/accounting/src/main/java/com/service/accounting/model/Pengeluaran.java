package com.service.accounting.model;

/**
 * POJO dari objek Pengeluaran
 */
public class Pengeluaran {
    private int idpengeluaran;
    private String tanggal;
    private String keterangan;
    private long jumlah;

    public int getIdpengeluaran() {
        return idpengeluaran;
    }

    public void setIdpengeluaran(int idpengeluaran) {
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

    public long getJumlah() {
        return jumlah;
    }

    public void setJumlah(long jumlah) {
        this.jumlah = jumlah;
    }
}
