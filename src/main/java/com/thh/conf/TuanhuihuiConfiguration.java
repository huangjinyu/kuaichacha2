package com.thh.conf;

import com.hqz.service.GoodsService;
import com.kcc.conf.KuaichachaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TuanhuihuiConfiguration {
    public TuanhuihuiConfiguration() {
        System.out.println("TuanhuihuiConfiguration配置类正在被扫描...");
    }

}
