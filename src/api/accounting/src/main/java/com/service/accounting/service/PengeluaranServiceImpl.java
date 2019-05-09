package com.service.accounting.service;

import com.service.accounting.exception.InputFormatException;
import com.service.accounting.model.Pengeluaran;
import com.service.accounting.model.PengeluaranMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Implementasi dari interface PengeluaranService
 */
@Service
public class PengeluaranServiceImpl implements PengeluaranService {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Contoh query pakai update(), cocok untuk query yang tidak return object
    @Override
    public Pengeluaran newPengeluaran(Pengeluaran partialValue) {
        String sql = "INSERT INTO pengeluaran(peng_tgl, peng_desc, peng_jumlah) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, partialValue.getTanggal(), partialValue.getKeterangan(), partialValue.getJumlah());
        return getLatestPengeluaran();
    }

    // Contoh query pakai queryForObject(), cocok untuk query yang pasti me-return tepat satu objek.
    private Pengeluaran getLatestPengeluaran() {
        String sql = "SELECT * FROM pengeluaran ORDER BY peng_id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new PengeluaranMapper());
    }

    private Pengeluaran getPengeluaranById(int idpengeluaran) {
        String sql = "SELECT * FROM pengeluaran WHERE peng_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ idpengeluaran }, new PengeluaranMapper());
    }

    @Override
    public Pengeluaran updatePengeluaran(Pengeluaran partialValue) {
        if (partialValue.getTanggal() != null) {
            jdbcTemplate.update("UPDATE pengeluaran SET peng_tgl=? WHERE peng_id=?", 
                    partialValue.getTanggal(), partialValue.getIdPengeluaran());
        }
        if (partialValue.getJumlah() != null) {
            jdbcTemplate.update("UPDATE pengeluaran SET peng_jumlah=? WHERE peng_id=?", 
                    partialValue.getJumlah(), partialValue.getIdPengeluaran());
        }
        if (partialValue.getKeterangan() != null) {
            jdbcTemplate.update("UPDATE pengeluaran SET peng_desc=? WHERE peng_id=?", 
                    partialValue.getKeterangan(), partialValue.getIdPengeluaran());
        }
        return getPengeluaranById(partialValue.getIdPengeluaran());
    }

    // Contoh query pakai query(), cocok untuk query yang mungkin mereturn lebih dari satu objek
    @Override
    public List<Pengeluaran> getPengeluaran(Integer start, Integer limit) {
        if (start != null) {
            if (start < 0) {
                throw new InputFormatException("Start value must greater than 0.");
            }
            if (limit != null) {
                if (limit < 1) {
                    throw new InputFormatException("Limit value must greater than 0.");
                }
                return jdbcTemplate.query("SELECT * FROM pengeluaran LIMIT ?, ?",
                        new PengeluaranMapper(), start, limit);
            } else {
                return jdbcTemplate.query("SELECT * FROM pengeluaran LIMIT ?, ?",
                        new PengeluaranMapper(), start, 30);
            }
        } else {
            if (limit != null) {
                if (limit < 1) {
                    throw new InputFormatException("Limit value must greater than 0.");
                }
                return jdbcTemplate.query("SELECT * FROM pengeluaran LIMIT ?, ?",
                        new PengeluaranMapper(), 0, limit);
            } else {
                return jdbcTemplate.query("SELECT * FROM pengeluaran LIMIT 300", new PengeluaranMapper());
            }
        }
    }

    @Override
    public Integer getNumberOfPengeluaran() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM pengeluaran",
                (rs, i) -> rs.getInt(1));
    }

    @Override
    public List<Pengeluaran> getPengeluaranByPeriod(String tahun) {
        String sql = "SELECT * FROM pengeluaran WHERE EXTRACT(YEAR FROM peng_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ tahun }, new PengeluaranMapper());
    }

    @Override
    public List<Pengeluaran> getPengeluaranByPeriod(String tahun, String bulan) {
        String sql = "SELECT * FROM pengeluaran WHERE EXTRACT(YEAR FROM peng_tgl)=? AND EXTRACT(MONTH FROM peng_tgl)=?";
        return jdbcTemplate.query(sql, new Object[]{ tahun, bulan }, new PengeluaranMapper());
    }
}
