package com.service.accounting.repository;

import com.service.accounting.model.Pengeluaran;
import com.service.accounting.model.PengeluaranMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PengeluaranRepositoryImpl implements PengeluaranRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Pengeluaran save(Pengeluaran data) {
        Integer id = jdbcTemplate.queryForObject("SELECT insert_pengeluaran(?, ?, ?)",
                new Object[]{ data.getTanggal(), data.getKeterangan(), data.getJumlah() },
                (rs, i) -> rs.getInt(1));
        return get(id);
    }

    @Override
    public Pengeluaran update(Pengeluaran data) {
        String sql = "UPDATE pengeluaran SET peng_tgl=?, peng_desc=?, peng_jumlah=? WHERE peng_id=?";
        jdbcTemplate.update(sql, data.getTanggal(), data.getKeterangan(), data.getJumlah(), data.getIdPengeluaran());
        return get(data.getIdPengeluaran());
    }

    @Override
    public Pengeluaran get(Integer id) {
        String sql = "SELECT * FROM pengeluaran WHERE peng_id=?";
        Pengeluaran result;
        try {
            result = jdbcTemplate.queryForObject(sql, new Object[]{id}, new PengeluaranMapper());
        } catch (IncorrectResultSizeDataAccessException e) {
            result = null;
        }
        return result;
    }

    @Override
    public List<Pengeluaran> get(Integer start, Integer limit) {
        return jdbcTemplate.query("SELECT * FROM pengeluaran LIMIT ?, ?",
                new PengeluaranMapper(), start, limit);
    }

    @Override
    public List<Pengeluaran> getByPeriod(String year) {
        String sql = "SELECT * FROM pengeluaran WHERE EXTRACT(YEAR FROM peng_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ year }, new PengeluaranMapper());
    }

    @Override
    public List<Pengeluaran> getByPeriod(String year, String month) {
        String sql = "SELECT * FROM pengeluaran WHERE EXTRACT(YEAR FROM peng_tgl)=? AND EXTRACT(MONTH FROM peng_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ year, month }, new PengeluaranMapper());
    }

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM pengeluaran",
                (rs, i) -> rs.getInt(1));
    }
}
