package com.ypp.controller;

import com.ypp.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.ResponseResult;

@RestController
@RequestMapping("/terminal")
public class TerminalController {
    @Autowired
    TerminalService terminalService;

    @PostMapping("/add")
    public ResponseResult addTerminal(String name,String desc){

        return terminalService.addTerminal(name,desc);
    }
    @PostMapping("/aroundsearch")
    public ResponseResult aroundSearch(String center,Integer radius){
        return terminalService.aroundSearch(center,radius);
    }
}