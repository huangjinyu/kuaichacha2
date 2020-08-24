package com.kcc.pojo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class TestRequestVO {
    @NotNull(message = "ID 不能为空", groups = {IUpdate.class})
    private Integer ID;

    @NotNull(message = "年龄不能为空", groups = {ICreate.class, IUpdate.class})
    @Range(max = 150, min = 1, message = "年龄范围应该在1-150内", groups = {ICreate.class, IUpdate.class})
    private Integer age;

    public interface ICreate {
    }

    public interface IUpdate {
    }
}
