package com.onepeice.fmmall.controller;

import com.onepeice.fmmall.entity.ShoppingCart;
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
    @GetMapping("/listbytoken")
    public ResultVo getShopcar(@RequestHeader("token") String token,String userId){
        ResultVo ResultVo = shopCarService.getShopcar(token);
        return ResultVo;
    }

    @PostMapping("/add")
    public ResultVo addShoppingCart(@RequestBody ShoppingCart cart, @RequestHeader("token")String token){
        System.out.println("已经进入");
        ResultVo ResultVo = shopCarService.addShoppingCart(cart);

        return ResultVo;
    }
    
    @GetMapping("/list")
    @ApiImplicitParam(dataType = "int",name = "userId", value = "用户ID",required = true)
    public ResultVo list(Integer userId,@RequestHeader("token")String token){
        ResultVo ResultVo = shopCarService.listShoppingCartsByUserId(userId);
        return ResultVo;
    }

    @PutMapping("/update/{cid}/{cnum}")
    public ResultVo updateNum(@PathVariable("cid") Integer cartId,
                              @PathVariable("cnum") Integer cartNum,
                              @RequestHeader("token") String token){
        ResultVo ResultVo = shopCarService.updateCartNum(cartId, cartNum);
        return ResultVo;
    }

    @GetMapping("/listbycids")
    @ApiImplicitParam(dataType = "String",name = "cids", value = "选择的购物车记录的id",required = true)
    public ResultVo listByCids(String cids, @RequestHeader("token")String token){
        ResultVo ResultVo = shopCarService.listShoppingCartsByCids(cids);
        return ResultVo;
    }
}
