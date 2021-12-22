package com.onepeice.fmmall.dao;

import com.onepeice.fmmall.entity.ShoppingCart;
import com.onepeice.fmmall.entity.ShoppingCartVO;
import com.onepeice.fmmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper extends GeneralDAO<ShoppingCart> {
    public List<ShoppingCartVO> selectShopcartByUserId(int userId);
    public int updateCartnumByCartid(@Param("cartId") int cartId,
                                     @Param("cartNum") int cartNum);

    public List<ShoppingCartVO> selectShopcartByCids(@Param("cids")List<Integer> cids);
}