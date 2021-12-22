package com.onepeice.fmmall.service;

import com.onepeice.fmmall.entity.ShoppingCart;
import com.onepeice.fmmall.vo.ResultVo;

public interface ShopCarService {

    public ResultVo getShopcar(String token);
    public ResultVo addShoppingCart(ShoppingCart cart);
    public ResultVo listShoppingCartsByUserId(int userid);
    public ResultVo updateCartNum(int cartId,int cartNum);
    public ResultVo listShoppingCartsByCids(String cids);

}
