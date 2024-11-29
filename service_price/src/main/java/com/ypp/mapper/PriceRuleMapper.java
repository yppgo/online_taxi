package com.ypp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import ypp.dto.PriceRule;

@Repository
public interface PriceRuleMapper extends BaseMapper<PriceRule> {

}
