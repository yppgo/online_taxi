package com.ypp.service_passenger_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import ypp.dto.PassengerUser;

@Repository
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {
    //新建接口，注册、扫描mapper
}
