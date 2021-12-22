package com.onepeice.fmmall.controller;

import com.onepeice.fmmall.service.UserAddrService;
import com.onepeice.fmmall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/useraddr")
@CrossOrigin
@Api(value = "提供收货地址相关接口",tags = "收货地址管理")
public class UserAddrController {
    @Autowired
    private UserAddrService userAddrService;

    @GetMapping("/list")
    @ApiImplicitParam(dataType = "int",name = "userId", value = "用户ID",required = true)
    public ResultVo listAddr(Integer userId, @RequestHeader("token") String token){
        ResultVo ResultVo = userAddrService.listAddrsByUid(userId);
        return ResultVo;
    }

}
