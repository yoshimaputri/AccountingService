package com.service.accounting.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PendapatanMapper implements RowMapper<Pendapatan> {
    @Override
    public Pendapatan mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pendapatan pendapatan = new Pendapatan();
        pendapatan.setIdPendapatan(rs.getInt(1));
        pendapatan.setIdRestaurant(rs.getString(2));
        pendapatan.setTanggal(rs.getString(3));
        pendapatan.setJumlah(rs.getLong(4));

        return pendapatan;
    }
}
