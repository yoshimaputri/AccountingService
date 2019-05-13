package com.service.accounting.model;

/**
 * POJO dari objek Pengeluaran
 */
public class Pengeluaran {
    private Integer idPengeluaran;
    private String idRestaurant;
    private String tanggal;
    private String keterangan;
    private Long jumlah;

    public Integer getIdPengeluaran() {
        return idPengeluaran;
    }

    public void setIdPengeluaran(Integer idPengeluaran) {
        this.idPengeluaran = idPengeluaran;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
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
