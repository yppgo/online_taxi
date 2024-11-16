package com.ypp.service_passenger_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypp.service_passenger_user.dto.PassengerUser;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {
}
