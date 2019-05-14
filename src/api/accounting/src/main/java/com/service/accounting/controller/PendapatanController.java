package com.service.accounting.controller;

import com.service.accounting.exception.NotAllowedException;
import com.service.accounting.model.Pendapatan;
import com.service.accounting.service.PendapatanService;
import com.service.accounting.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Pendapatan newPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @RequestBody Pendapatan pendapatan
    ) {
        InputValidator.validateInputData(pendapatan, true);
        return pendapatanService.newPendapatan(pendapatan);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH },
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Pendapatan editPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("id") int idPendapatan,
            @RequestBody Pendapatan pendapatan
    ) {
        InputValidator.validateInputData(pendapatan, false);
        pendapatan.setIdPendapatan(idPendapatan);
        return pendapatanService.updatePendapatan(pendapatan);
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Pendapatan> getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @RequestParam(name = "idrest", required = false) String idRestaurant,
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "limit", required = false) Integer limit,
            HttpServletResponse response
    ) {
        response.addHeader("X-Total-Count", pendapatanService.getNumberOfPendapatan().toString());
        return pendapatanService.getPendapatan(idRestaurant, start, limit);
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Pendapatan> getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") String tahun
    ) {
        InputValidator.checkValidPath(tahun, null);
        return pendapatanService.getPendapatanByPeriod(tahun);
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}/{bulan}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Pendapatan> getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") String tahun,
            @PathVariable("bulan") String bulan
    ) {
        InputValidator.checkValidPath(tahun, bulan);
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
            @PathVariable("id") int idPendapatan
    ) {
        throw new NotAllowedException();
    }
}
