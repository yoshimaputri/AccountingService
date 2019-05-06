package com.service.accounting.controller;

import com.service.accounting.service.PendapatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pendapatan")
public class PendapatanController {
    private PendapatanService pendapatanService;

    @Autowired
    public void setPendapatanService(PendapatanService pendapatanService) {
        this.pendapatanService = pendapatanService;
    }
}
