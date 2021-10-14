package com.onepeice.fmmall.service.impl;

import com.onepeice.fmmall.dao.ProductImgMapper;
import com.onepeice.fmmall.dao.ProductMapper;
import com.onepeice.fmmall.dao.ProductParamsMapper;
import com.onepeice.fmmall.dao.ProductSkuMapper;
import com.onepeice.fmmall.entity.*;
import com.onepeice.fmmall.service.ProductService;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImgMapper productImgMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductParamsMapper productParamsMapper;

    public ResultVo listRecommendProducts() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        ResultVo ResultVo = new ResultVo(ResStatus.OK, "success", productVOS);
        return ResultVo;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVo getProductBasicInfo(String productId) {
        //1.商品基本信息
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        criteria.andEqualTo("productStatus",1);//状态为1表示上架商品
        List<Product> products = productMapper.selectByExample(example);
        if(products.size()>0){
            //2.商品图片
            Example example1 = new Example(ProductImg.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("itemId",productId);
            List<ProductImg> productImgs = productImgMapper.selectByExample(example1);
            //3.商品套餐
            Example example2 = new Example(ProductSku.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("productId",productId);
            criteria2.andEqualTo("status",1);
            List<ProductSku> productSkus = productSkuMapper.selectByExample(example2);

            HashMap<String,Object> basicInfo = new HashMap<>();
            basicInfo.put("product",products.get(0));
            basicInfo.put("productImgs",productImgs);
            basicInfo.put("productSkus",productSkus);
            return new ResultVo(ResStatus.OK,"success",basicInfo);
        }else{
            return new ResultVo(ResStatus.NO,"查询的商品不存在！",null);
        }
    }

    @Override
    public ResultVo getProductParamsById(String productId) {
        Example example = new Example(ProductParams.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        List<ProductParams> productParams = productParamsMapper.selectByExample(example);
        if(productParams.size()>0){
            return new ResultVo(ResStatus.OK,"success",productParams.get(0));
        }else{
            return new ResultVo(ResStatus.NO,"此商品可能为三无产品",null);
        }
    }
}
