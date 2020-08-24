package com.hqz.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// @Component("goods.settings-com.hqz.conf.GoodsServiceProperties")
@ConfigurationProperties("goods.settings")
@Data
public class GoodsServiceProperties {
//    private Integer imageMaxSize;
//    private Integer imageMinSize;
//    private Integer imageMaxWidth;
//    private Integer imageMaxHeight;
//    private Integer imageMinWidth;
//    private Integer imageMinHeight;
}