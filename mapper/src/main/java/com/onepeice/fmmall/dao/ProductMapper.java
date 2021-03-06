package com.onepeice.fmmall.dao;

import com.onepeice.fmmall.entity.Product;
import com.onepeice.fmmall.entity.ProductVO;
import com.onepeice.fmmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductMapper extends GeneralDAO<Product> {
    public List<ProductVO> selectRecommendProducts();

    /**
     * 查询指定一级类别下销量最高的6个商品
     * @param cid
     * @return
     */
    public List<ProductVO> selectTop6ByCategory(int cid);
}