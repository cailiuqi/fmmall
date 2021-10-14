package com.onepeice.fmmall.controller;

import com.onepeice.fmmall.service.ProductService;
import com.onepeice.fmmall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(value = "提供商品详情信息相关接口",tags = "商品管理")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("商品详细信息查询接口")
    @GetMapping("/detail-info/{pid}")
    public ResultVo getProductBasicInfo(@PathVariable String pid){
        return productService.getProductBasicInfo(pid);
    }
}
