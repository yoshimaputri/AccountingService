package com.service.accounting.service;

import com.service.accounting.model.Pendapatan;
import com.service.accounting.model.PendapatanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class PendapatanServiceImpl implements PendapatanService {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Pendapatan newPendapatan(String tanggal, long jumlah) {
        String sql = "INSERT INTO pendapatan(pend_tgl, pend_jumlah) VALUES (?, ?)";
        jdbcTemplate.update(sql, tanggal, jumlah);
        return getLatestPendapatan();
    }

    private Pendapatan getLatestPendapatan() {
        String sql = "SELECT * FROM pendapatan ORDER BY pend_id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new PendapatanMapper());
    }

    @Override
    public Pendapatan getPendapatanById(int idpendapatan) {
        String sql = "SELECT * FROM pendapatan WHERE pend_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ idpendapatan }, new PendapatanMapper());
    }

    @Override
    public void changeTanggal(int idpendapatan, String tanggal) {
        jdbcTemplate.update("UPDATE pendapatan SET pend_tgl=? WHERE pend_id=?", tanggal, idpendapatan);
    }

    @Override
    public void changeJumlah(int idpendapatan, long jumlah) {
        jdbcTemplate.update("UPDATE pendapatan SET pend_jumlah=? WHERE pend_id=?", jumlah, idpendapatan);
    }

    @Override
    public List<Pendapatan> getPendapatan() {
        return jdbcTemplate.query("SELECT * FROM pendapatan", new PendapatanMapper());
    }

    @Override
    public List<Pendapatan> getPendapatan(int tahun) {
        String sql = "SELECT * FROM pendapatan WHERE EXTRACT(YEAR FROM pend_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ tahun }, new PendapatanMapper());
    }

    @Override
    public List<Pendapatan> getPendapatan(int tahun, int bulan) {
        String sql = "SELECT * FROM pendapatan WHERE EXTRACT(YEAR FROM pend_tgl)=? AND EXTRACT(MONTH FROM pend_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ tahun, bulan }, new PendapatanMapper());
    }
}
