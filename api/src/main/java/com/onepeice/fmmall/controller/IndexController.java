package com.onepeice.fmmall.controller;

import com.onepeice.fmmall.service.CategoryService;
import com.onepeice.fmmall.service.IndexImgService;
import com.onepeice.fmmall.service.ProductService;
import com.onepeice.fmmall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/index")

@CrossOrigin
@Api(value = "轮播图相关接口",tags = "轮播图管理")
public class IndexController {

    @Autowired
    private IndexImgService indexImgService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;


    @GetMapping("/indeximg")
    @ApiOperation("轮播图初始化接口")
    //这是一个测试
    public ResultVo listIndex(){
        ResultVo resultVo = indexImgService.listIndex();
        return resultVo;
    }

    @GetMapping("/category-list")
    @ApiOperation("商品分类查询接口")
    public ResultVo listCatetory(){
        return categoryService.listCategories();
    }

    @GetMapping("/list-recommends")
    @ApiOperation("新品推荐接口")
    public ResultVo listRecommendProducts() {
        return productService.listRecommendProducts();
    }

    @GetMapping("/category-recommends")
    @ApiOperation("分类推荐接口")
    public ResultVo listRecommendProductsByCategory(){
        return categoryService.listFirstLevelCategories();
    }

}
