package com.onepeice.fmmall.service;


import com.onepeice.fmmall.vo.ResultVo;

public interface ProductService {

    public ResultVo listRecommendProducts();

    public ResultVo getProductBasicInfo(String productId);

    public ResultVo getProductParamsById(String productId);

}

