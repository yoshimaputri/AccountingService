package com.service.accounting.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

/**
 * POJO dari objek Pengeluaran
 */
@Entity
@Table(name = "pengeluaran")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pengeluaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peng_id")
    private Integer idPengeluaran;

    @Column(name = "resto_id")
    private String idRestaurant;

    @Column(name = "peng_tgl")
    private String tanggal;

    @Column(name = "peng_desc")
    private String keterangan;

    @Column(name = "peng_jumlah")
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
