package com.tencent.wxcloudrun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.tencent.wxcloudrun.dao"})
//@ComponentScan(value = {"com.tencent.wxcloudrun"})
public class WxCloudRunApplication {  

  public static void main(String[] args) {
    SpringApplication.run(WxCloudRunApplication.class, args);
  }
}
