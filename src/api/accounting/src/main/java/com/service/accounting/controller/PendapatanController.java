package com.service.accounting.controller;

import com.service.accounting.exception.NotAllowedException;
import com.service.accounting.model.Pendapatan;
import com.service.accounting.service.PendapatanService;
import com.service.accounting.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/pendapatan")
public class PendapatanController {
    private PendapatanService pendapatanService;

    @Autowired
    public void setPendapatanService(PendapatanService pendapatanService) {
        this.pendapatanService = pendapatanService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Pendapatan newPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @RequestParam("tanggal") String tanggal,
            @RequestParam("jumlah") long jumlah
    ) {
        InputValidator.checkValidDate(tanggal);

        return pendapatanService.newPendapatan(tanggal, jumlah);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public Pendapatan editPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("id") int idpendapatan,
            @RequestParam(name = "tanggal", required = false) String tanggal,
            @RequestParam(name = "jumlah", required = false) Long jumlah
    ) {
        if (tanggal != null) {
            InputValidator.checkValidDate(tanggal);
            pendapatanService.changeTanggal(idpendapatan, tanggal);
        }
        if (jumlah != null) {
            pendapatanService.changeJumlah(idpendapatan, jumlah);
        }

        return pendapatanService.getPendapatanById(idpendapatan);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Pendapatan> getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "limit", required = false) Integer limit,
            HttpServletResponse response
    ) {
        response.addHeader("X-Total-Count", pendapatanService.getNumberOfPendapatan().toString());
        return pendapatanService.getPendapatan(start, limit);
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}", method = RequestMethod.GET)
    public List<Pendapatan> getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") int tahun
    ) {
        return pendapatanService.getPendapatanByPeriod(tahun);
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}/{bulan}", method = RequestMethod.GET)
    public List<Pendapatan> getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") int tahun,
            @PathVariable("bulan") int bulan
    ) {
        return pendapatanService.getPendapatanByPeriod(tahun, bulan);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void deletePendapatan() {
        throw new NotAllowedException();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePendapatan(
            @PathVariable("id") int idpendapatan
    ) {
        throw new NotAllowedException();
    }
}
