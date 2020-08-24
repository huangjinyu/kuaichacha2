package com.kcc.pojo.vo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class Test1VO {
    @JsonView({TestInclude1VO.class, AView.class, IDView.class})
    private Integer ID;

    @JsonView({TestInclude1VO.class, TestInclude2VO.class, AView.class, BView.class, IDAndNameView.class})
    private String name;

    @JsonView({AView.class})
    private Integer age;

    @JsonView({TestInclude1VO.class, TestInclude2VO.class, AView.class, BView.class})
    private String city;

    private String address;


    public interface IDView {
    }

    public interface IDAndNameView extends IDView {
    }

    public interface AView {
    }

    public interface BView {
    }

    public interface CView {
    }

    public interface DView {
    }
}
