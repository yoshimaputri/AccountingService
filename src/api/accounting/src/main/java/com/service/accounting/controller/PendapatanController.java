package com.service.accounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.accounting.exception.InputFormatException;
import com.service.accounting.exception.NotAllowedException;
import com.service.accounting.model.Pendapatan;
import com.service.accounting.service.PendapatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            @RequestBody String body
    ) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Pendapatan pendapatan = mapper.readValue(body, Pendapatan.class);
            return pendapatanService.newPendapatan(pendapatan);
        } catch (IOException e) {
            throw new InputFormatException();
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Pendapatan editPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("id") int idpendapatan,
            @RequestBody String body
    ) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Pendapatan pendapatan = mapper.readValue(body, Pendapatan.class);
            pendapatan.setIdpendapatan(idpendapatan);
            return pendapatanService.updatePendapatan(pendapatan);
        } catch (IOException e) {
            throw new InputFormatException();
        }
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
