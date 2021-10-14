package com.onepeice.fmmall.controller;

import com.onepeice.fmmall.service.ShopCarService;
import com.onepeice.fmmall.vo.ResultVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopcart")
@CrossOrigin
@Api(value = "购物车相关接口",tags = "购物车管理")
public class ShopCarController {


    @Autowired
    private ShopCarService shopCarService;

    @ApiOperation("购物车初始化接口")
    @ApiImplicitParam(dataType = "String",name = "token",value = "token",required = true)
    @GetMapping("/list")
    public ResultVo getShopcar(@RequestHeader("token") String token,String userId){
        ResultVo resultVo = shopCarService.getShopcar(token);
        return resultVo;
    }
}
