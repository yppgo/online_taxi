package com.ypp.service;

import com.ypp.remote.DriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypp.dto.DriverCarBindingRelationship;
import ypp.dto.ResponseResult;

@Service
public class DriverCarRelationshipService {
    @Autowired
    DriverUserClient driverUserClient;
    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {

        return driverUserClient.bind(driverCarBindingRelationship);
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {

        return driverUserClient.unBind(driverCarBindingRelationship);

    }
}
