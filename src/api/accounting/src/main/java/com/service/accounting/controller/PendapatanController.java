package com.service.accounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.service.accounting.exception.NotAllowedException;
import com.service.accounting.model.Pendapatan;
import com.service.accounting.service.PendapatanService;
import com.service.accounting.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pendapatan")
public class PendapatanController {
    private PendapatanService pendapatanService;
    private ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    @Autowired
    public void setPendapatanService(PendapatanService pendapatanService) {
        this.pendapatanService = pendapatanService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String newPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @ModelAttribute Pendapatan pendapatan
    ) {
        InputValidator.validateInputData(pendapatan, true);
        Pendapatan result = pendapatanService.newPendapatan(pendapatan);
        try {
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH },
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("id") int idPendapatan,
            @ModelAttribute Pendapatan pendapatan
    ) {
        try {
            pendapatan.setIdPendapatan(idPendapatan);
            InputValidator.validateInputData(pendapatan, false);
            Pendapatan result = pendapatanService.updatePendapatan(pendapatan);
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "limit", required = false) Integer limit,
            HttpServletResponse response
    ) {
        response.addHeader("X-Total-Count", pendapatanService.getNumberOfPendapatan().toString());
        List<Pendapatan> result = pendapatanService.getPendapatan(start, limit);
        try {
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") int tahun
    ) {
        List<Pendapatan> result =  pendapatanService.getPendapatanByPeriod(tahun);
        try {
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}/{bulan}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPendapatan(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") int tahun,
            @PathVariable("bulan") int bulan
    ) {
        List<Pendapatan> result = pendapatanService.getPendapatanByPeriod(tahun, bulan);
        try {
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
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
