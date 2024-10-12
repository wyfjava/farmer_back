//package com.tencent.wxcloudrun.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.codec.JsonJacksonCodec;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConditionalOnClass(Config.class)
//@EnableConfigurationProperties(RedissonProperties.class)
//public class RedissonConfig {
//    @Autowired
//    private RedissonProperties redissonProperties;
//
//    @Bean
//    public RedissonClient redissonClient(){
//        Config config = new Config();
//        config.setCodec(new JsonJacksonCodec());
//        config.useSingleServer()
//                .setAddress(redissonProperties.getAddress())
//                .setDatabase(redissonProperties.getDatabase())
//                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
//                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
//                .setDnsMonitoringInterval(redissonProperties.getDnsMonitoringInterval())
//                .setConnectTimeout(redissonProperties.getConnectTimeout())
//                .setTimeout(redissonProperties.getTimeout())
//                .setPassword(redissonProperties.getPassword())
//                .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
//                .setRetryAttempts(redissonProperties.getRetryAttempts())
//                .setRetryInterval(redissonProperties.getRetryInterval());
//        return Redisson.create(config);
//    }
//}
