package com.ypp.controller;


import com.ypp.service.DriverCarBindingRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import ypp.dto.DriverCarBindingRelationship;
import ypp.dto.ResponseResult;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 于鹏鹏
 * @since 2024-12-03
 */
@RestController
@RequestMapping("/driver_car_binding_relationship")
public class DriverCarBindingRelationshipController {
    @Autowired
    DriverCarBindingRelationshipService driverCarBindingRelationshipService;
    @PostMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }
    @PostMapping("/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }

}
