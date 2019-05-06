package com.service.accounting.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PendapatanMapper implements RowMapper<Pendapatan> {
    @Override
    public Pendapatan mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pendapatan pendapatan = new Pendapatan();
        pendapatan.setIdpendapatan(rs.getInt(1));
        pendapatan.setTanggal(rs.getString(2));
        pendapatan.setJumlah(rs.getLong(3));

        return pendapatan;
    }
}
