package com.service.accounting.service;

import com.service.accounting.model.Pengeluaran;
import com.service.accounting.model.PengeluaranMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Implementasi dari interface PengeluaranService
 */
@Service
public class PengeluaranServiceImpl implements PengeluaranService {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    // Contoh query pakai update(), cocok untuk query yang tidak return object
    @Override
    public Pengeluaran newPengeluaran(String tanggal, String keterangan, long jumlah) {
        String sql = "INSERT INTO pengeluaran(peng_tgl, peng_desc, peng_jumlah) VALUES (?, ?, ?)";
        jdbcTemplateObject.update(sql, tanggal, keterangan, jumlah);
        return getPengeluaran(tanggal, keterangan, jumlah);
    }

    // Contoh query pakai queryForObject(), cocok untuk query yang pasti me-return tepat satu objek.
    private Pengeluaran getPengeluaran(String tanggal, String keterangan, long jumlah) {
        String sql = "SELECT * FROM pengeluaran WHERE peng_tgl=? AND peng_desc=? AND peng_jumlah=?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{ tanggal, keterangan, jumlah },
                new PengeluaranMapper());
    }

    // TODO: add more service here
}
