package com.service.accounting.model;

import javax.persistence.*;

@Entity
public class Pendapatan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pend_id")
    private Integer idPendapatan;

    @Column(name = "pend_tgl")
    private String tanggal;

    @Column(name = "pend_jumlah")
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
