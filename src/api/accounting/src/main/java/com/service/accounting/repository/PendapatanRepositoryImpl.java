package com.service.accounting.repository;

import com.service.accounting.model.Pendapatan;
import com.service.accounting.model.PendapatanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PendapatanRepositoryImpl implements PendapatanRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Pendapatan save(Pendapatan data) {
        Integer id = jdbcTemplate.queryForObject("SELECT insert_pendapatan(?, ?)",
                new Object[]{ data.getTanggal(), data.getJumlah() }, (rs, i) -> rs.getInt(1));
        return get(id);
    }

    @Override
    public Pendapatan update(Pendapatan data) {
        jdbcTemplate.update("UPDATE pendapatan SET pend_tgl=?, pend_jumlah=? WHERE pend_id=?",
                data.getTanggal(), data.getJumlah(), data.getIdPendapatan());
        return get(data.getIdPendapatan());
    }

    @Override
    public Pendapatan get(Integer id) {
        String sql = "SELECT * FROM pendapatan WHERE pend_id=?";
        Pendapatan result;
        try {
            result = jdbcTemplate.queryForObject(sql, new Object[]{id}, new PendapatanMapper());
        } catch (IncorrectResultSizeDataAccessException e) {
            result = null;
        }
        return result;
    }

    @Override
    public List<Pendapatan> get(Integer start, Integer limit) {
        return jdbcTemplate.query("SELECT * FROM pendapatan LIMIT ?, ?",
                new PendapatanMapper(), start, limit);
    }

    @Override
    public List<Pendapatan> getByPeriod(String year) {
        String sql = "SELECT * FROM pendapatan WHERE EXTRACT(YEAR FROM pend_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ year }, new PendapatanMapper());
    }

    @Override
    public List<Pendapatan> getByPeriod(String year, String month) {
        String sql = "SELECT * FROM pendapatan WHERE EXTRACT(YEAR FROM pend_tgl)=? AND EXTRACT(MONTH FROM pend_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ year, month }, new PendapatanMapper());
    }

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM pendapatan",
                (rs, i) -> rs.getInt(1));
    }
}
