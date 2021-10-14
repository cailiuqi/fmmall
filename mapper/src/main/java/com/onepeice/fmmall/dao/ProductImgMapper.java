package com.onepeice.fmmall.dao;

import com.onepeice.fmmall.entity.ProductImg;
import com.onepeice.fmmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImgMapper extends GeneralDAO<ProductImg> {
    public List<ProductImg> selectProductImgByProductId(int productId);
}