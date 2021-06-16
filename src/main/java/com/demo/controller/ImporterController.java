package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.ImporterService;

@RestController
@RequestMapping("/import")
public class ImporterController {

    @Autowired
    private ImporterService importerService;

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public String load(){
        return importerService.loadData();
    }
}
