package com.ypp.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MysqlGene {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service_driver?characterEncoding=utf-8&serverTimeZone=GMT%2B8&useSSL=false",
                "root","root").globalConfig(builder -> {
            builder.author("于鹏鹏").fileOverride().outputDir("C:/java项目/新建文件夹/online_taxi/service_driver_user/src/main/java");
        }).packageConfig(builder -> {
            builder.parent("com.ypp").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                    "C:/java项目/新建文件夹/online_taxi/service_driver_user/src/main/java/com/ypp/mapper"));
        }).strategyConfig(builder -> {
            builder.addInclude("driver_car_binding_relationship");
        }).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}