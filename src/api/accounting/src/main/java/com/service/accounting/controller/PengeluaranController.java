package com.service.accounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.service.accounting.exception.NotAllowedException;
import com.service.accounting.model.Pengeluaran;
import com.service.accounting.service.PengeluaranService;
import com.service.accounting.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Berisi mapping tiap request ke service yang ada
 */
@RestController
@RequestMapping("/pengeluaran")
public class PengeluaranController {
    private PengeluaranService pengeluaranService;
    private ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    // Setter injection lebih di-recommend oleh spring daripada field injection
    @Autowired
    public void setPengeluaranService(PengeluaranService pengeluaranService) {
        this.pengeluaranService = pengeluaranService;
    }

    // Best practice untuk selalu set response status jika yang di-return bukan HTTP Status 200
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addPengeluaran(
            // Lebih simple membaca header pakai @RequestHeader.
            // Parameter required masih false karena spek token masih belum turun.
            @RequestHeader(name = "token", required = false) String token,
            @RequestBody String input
    ) {
        try {
            Pengeluaran pengeluaran = mapper.readValue(input, Pengeluaran.class);
            InputValidator.validateInputData(pengeluaran, true);
            Pengeluaran result = pengeluaranService.newPengeluaran(pengeluaran);
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH },
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String changePengeluaran(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("id") int idPengeluaran,
            @RequestBody String input
    ) {
        try {
            Pengeluaran pengeluaran = mapper.readValue(input, Pengeluaran.class);
            InputValidator.validateInputData(pengeluaran, false);
            Pengeluaran result = pengeluaranService.updatePengeluaran(idPengeluaran, pengeluaran);
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPengeluaran(
            @RequestHeader(name = "token", required = false) String token,
            @RequestParam(name = "id", required = false) String idRestaurant,
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "limit", required = false) Integer limit,
            HttpServletResponse response
    ) {
        response.addHeader("X-Total-Count", pengeluaranService.getNumberOfPengeluaran().toString());
        List<Pengeluaran> result = pengeluaranService.getPengeluaran(idRestaurant, start, limit);
        try {
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPengeluaran(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") String tahun
    ) {
        InputValidator.checkValidPath(tahun, null);
        List<Pengeluaran> result = pengeluaranService.getPengeluaranByPeriod(tahun);
        try {
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{tahun}/{bulan}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPengeluaran(
            @RequestHeader(name = "token", required = false) String token,
            @PathVariable("tahun") String tahun,
            @PathVariable("bulan") String bulan
    ) {
        InputValidator.checkValidPath(tahun, bulan);
        List<Pengeluaran> result = pengeluaranService.getPengeluaranByPeriod(tahun, bulan);
        try {
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void deletePengeluaran() {
        throw new NotAllowedException();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePengeluaran(
            @PathVariable("id") int idPengeluaran
    ) {
        throw new NotAllowedException();
    }
}
