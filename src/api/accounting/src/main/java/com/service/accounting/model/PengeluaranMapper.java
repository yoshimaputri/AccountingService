package com.service.accounting.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class untuk mapping dari database ke POJO Pengeluaran
 */
public class PengeluaranMapper implements RowMapper<Pengeluaran> {

    @Override
    public Pengeluaran mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pengeluaran pengeluaran = new Pengeluaran();
        pengeluaran.setIdPengeluaran(rs.getInt(1));
        pengeluaran.setTanggal(rs.getString(2));
        pengeluaran.setKeterangan(rs.getString(3));
        pengeluaran.setJumlah(rs.getLong(4));

        return pengeluaran;
    }
}
