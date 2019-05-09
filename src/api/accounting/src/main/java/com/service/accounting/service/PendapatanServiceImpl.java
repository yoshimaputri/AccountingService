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
    public Pendapatan newPendapatan(Pendapatan partialValue) {
        String sql = "INSERT INTO pendapatan(pend_tgl, pend_jumlah) VALUES (?, ?)";
        jdbcTemplate.update(sql, partialValue.getTanggal(), partialValue.getJumlah());
        return getLatestPendapatan();
    }

    private Pendapatan getLatestPendapatan() {
        String sql = "SELECT * FROM pendapatan ORDER BY pend_id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new PendapatanMapper());
    }

    private Pendapatan getPendapatanById(int idpendapatan) {
        String sql = "SELECT * FROM pendapatan WHERE pend_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ idpendapatan }, new PendapatanMapper());
    }

    @Override
    public Pendapatan updatePendapatan(Pendapatan partialValue) {
        if (partialValue.getTanggal() != null) {
            jdbcTemplate.update("UPDATE pendapatan SET pend_tgl=? WHERE pend_id=?", 
                    partialValue.getTanggal(), partialValue.getIdPendapatan());
        }
        if (partialValue.getJumlah() != null) {
            jdbcTemplate.update("UPDATE pendapatan SET pend_jumlah=? WHERE pend_id=?", 
                    partialValue.getJumlah(), partialValue.getIdPendapatan());
        }
        return getPendapatanById(partialValue.getIdPendapatan());
    }

    @Override
    public List<Pendapatan> getPendapatan(Integer start, Integer limit) {
        if (start != null) {
            if (limit != null) {
                return jdbcTemplate.query("SELECT * FROM pendapatan LIMIT ?, ?",
                        new PendapatanMapper(), start, limit);
            } else {
                return jdbcTemplate.query("SELECT * FROM pendapatan LIMIT ?, ?",
                        new PendapatanMapper(), start, 30);
            }
        } else {
            if (limit != null) {
                return jdbcTemplate.query("SELECT * FROM pendapatan LIMIT ?, ?",
                        new PendapatanMapper(), 0, limit);
            } else {
                return jdbcTemplate.query("SELECT * FROM pendapatan LIMIT 300", new PendapatanMapper());
            }
        }
    }

    @Override
    public Integer getNumberOfPendapatan() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM pendapatan",
                (rs, i) -> rs.getInt(1));
    }

    @Override
    public List<Pendapatan> getPendapatanByPeriod(String tahun) {
        String sql = "SELECT * FROM pendapatan WHERE EXTRACT(YEAR FROM pend_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ tahun }, new PendapatanMapper());
    }

    @Override
    public List<Pendapatan> getPendapatanByPeriod(String tahun, String bulan) {
        String sql = "SELECT * FROM pendapatan WHERE EXTRACT(YEAR FROM pend_tgl)=? AND EXTRACT(MONTH FROM pend_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ tahun, bulan }, new PendapatanMapper());
    }
}
