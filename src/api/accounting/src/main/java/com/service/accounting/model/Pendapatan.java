package com.service.accounting.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;

@Entity
@Table(name = "pendapatan")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pendapatan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pend_id")
    private Integer idPendapatan;

    @Column(name = "resto_id")
    private String idRestaurant;

    @Column(name = "pend_tgl")
    private String tanggal;

    @Column(name = "pend_desc")
    private String keterangan;

    @Column(name = "pend_jumlah")
    private Long jumlah;

    public Integer getIdPendapatan() {
        return idPendapatan;
    }

    public void setIdPendapatan(Integer idPendapatan) {
        this.idPendapatan = idPendapatan;
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
