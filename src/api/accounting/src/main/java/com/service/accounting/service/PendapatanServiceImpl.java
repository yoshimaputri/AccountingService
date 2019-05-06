package com.service.accounting.service;

import com.service.accounting.model.Pendapatan;
import com.service.accounting.model.PendapatanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class PendapatanServiceImpl implements PendapatanService {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public Pendapatan newPendapatan(String tanggal, long jumlah) {
        String sql = "INSERT INTO pendapatan(pend_tgl, pend_jumlah) VALUES (?, ?)";
        jdbcTemplateObject.update(sql, tanggal, jumlah);
        return getLatestPendapatan();
    }

    private Pendapatan getLatestPendapatan() {
        String sql = "SELECT * FROM pendapatan ORDER BY pend_id DESC LIMIT 1";
        return jdbcTemplateObject.queryForObject(sql, new PendapatanMapper());
    }
}
