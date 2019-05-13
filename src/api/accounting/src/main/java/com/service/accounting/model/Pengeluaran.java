package com.service.accounting.model;

import javax.persistence.*;

/**
 * POJO dari objek Pengeluaran
 */
@Entity
public class Pengeluaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peng_id")
    private Integer idPengeluaran;

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
