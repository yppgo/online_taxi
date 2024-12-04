package com.ypp.controller;

import com.ypp.service.DriverCarRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.DriverCarBindingRelationship;
import ypp.dto.ResponseResult;

@RestController
@RequestMapping("/driver_car_binding_relationship")
public class DriverCarBindingRealtionshipController {
    @Autowired
    DriverCarRelationshipService driverCarRelationshipService;
    @PostMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarRelationshipService.bind(driverCarBindingRelationship);
    }
    @PostMapping("/unbind")
    public ResponseResult unBind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarRelationshipService.unbind(driverCarBindingRelationship);
    }

}
