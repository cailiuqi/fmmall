package com.onepeice.fmmall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResultVo返回值" ,description = "返回前端的封装对象")
public class ResultVo {

    //响应给i前端的状态码
    @ApiModelProperty(value = "响应状态码")
    private int code;
    @ApiModelProperty(value = "响应前端的消息")
    private String msg;
    @ApiModelProperty(value = "响应给前端的数据")
    private Object  data;

}
