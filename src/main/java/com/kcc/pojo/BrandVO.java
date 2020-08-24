package com.kcc.pojo;

//import com.alibaba.fastjson.annotation.JSONField;
//import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
//@Builder
//@NoArgsConstructor
public class BrandVO {
    private String name;
    private String desc;
//    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
//    @JSONField(format = "我是fastjson yyyyMMdd")
    private Date createdDate;
}
