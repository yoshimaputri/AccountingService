package com.service.accounting.controller;

import com.service.accounting.exception.InputFormatException;
import com.service.accounting.exception.NotAllowedException;
import com.service.accounting.model.Pengeluaran;
import com.service.accounting.service.PengeluaranService;
import com.service.accounting.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Berisi mapping tiap request ke service yang ada
 */
@RestController
@RequestMapping("/pengeluaran")
public class PengeluaranController {
    private PengeluaranService pengeluaranService;

    // Setter injection lebih di-recommend oleh spring daripada field injection
    @Autowired
    public void setPengeluaranService(PengeluaranService pengeluaranService) {
        this.pengeluaranService = pengeluaranService;
    }

    // Best practice untuk selalu set response status jika yang di-return bukan HTTP Status 200
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Pengeluaran addPengeluaran(
            // Lebih simple membaca header pakai @RequestHeader.
            // Parameter required masih false karena spek token masih belum turun.
            @RequestHeader(name = "token", required = false) String token,
            @RequestParam("tanggal") String tanggal,
            @RequestParam("keterangan") String keterangan,
            @RequestParam("jumlah") long jumlah
    ) {
        // Cek validitas tanggal
        if (!Util.isValidDate(tanggal)) {
            throw new InputFormatException("Invalid date format. Expected 'YYYY-MM-DD'.");
        }

        return pengeluaranService.newPengeluaran(tanggal, keterangan, jumlah);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public Pengeluaran changePengeluaran(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("id") int idpengeluaran,
            @RequestParam(name = "tanggal", required = false) String tanggal,
            @RequestParam(name = "keterangan", required = false) String keterangan,
            @RequestParam(name = "jumlah", required = false) Long jumlah
    ) {
        if (tanggal != null) {
            if (Util.isValidDate(tanggal)) {
                pengeluaranService.changeTanggal(idpengeluaran, tanggal);
            } else {
                throw new InputFormatException("Invalid date format. Expected 'YYYY-MM-DD'.");
            }
        }
        if (jumlah != null) {
            pengeluaranService.changeJumlah(idpengeluaran, jumlah);
        }
        if (keterangan != null) {
            pengeluaranService.changeKeterangan(idpengeluaran, keterangan);
        }

        return pengeluaranService.getPengeluaranById(idpengeluaran);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Pengeluaran> getPengeluaran(
            @RequestHeader(name = "token", required = false) String token
    ) {
        return pengeluaranService.getPengeluaran();
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}", method = RequestMethod.GET)
    public List<Pengeluaran> getPengeluaran(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") int tahun
    ) {
        return pengeluaranService.getPengeluaran(tahun);
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}/{bulan}", method = RequestMethod.GET)
    public List<Pengeluaran> getPengeluaran(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") int tahun,
            @PathVariable("bulan") int bulan
    ) {
        return pengeluaranService.getPengeluaran(tahun, bulan);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void deletePengeluaran() {
        throw new NotAllowedException();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePengeluaran(
            @PathVariable("id") int idpengeluaran
    ) {
        throw new NotAllowedException();
    }
}
