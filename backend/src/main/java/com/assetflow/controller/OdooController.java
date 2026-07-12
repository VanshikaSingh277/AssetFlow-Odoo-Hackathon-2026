package com.assetflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assetflow.service.OdooService;

@RestController
@RequestMapping("/api/odoo")
public class OdooController {

    @Autowired
    private OdooService odooService;

    @PostMapping("/sync")
    public String sync() {
        return odooService.syncAssets();
    }
}