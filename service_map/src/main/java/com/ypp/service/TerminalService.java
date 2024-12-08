package com.ypp.service;

import com.ypp.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.ResponseResult;

import java.util.ResourceBundle;

@Service
public class TerminalService {
    @Autowired
    TerminalClient terminalClient;
    public ResponseResult addTerminal(String name,String desc){

        return terminalClient.addTerminal(name,desc);
    }
    public ResponseResult aroundSearch(String center,Integer radius){
        return terminalClient.aroundSearch(center,radius);
    }
}
