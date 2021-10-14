package com.onepeice.fmmall.service.impl;

import com.onepeice.fmmall.dao.ShoppingCartMapper;
import com.onepeice.fmmall.dao.UsersMapper;
import com.onepeice.fmmall.entity.ShoppingCart;
import com.onepeice.fmmall.entity.Users;
import com.onepeice.fmmall.service.ShopCarService;
import com.onepeice.fmmall.utils.Base64Utils;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ShopCarServiceImpl implements ShopCarService {


    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public ResultVo getShopcar(String token) {
        //拦截器中已经对token进行来校验，所以service中无需验证token
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectAll();
        return new ResultVo(ResStatus.OK,"success",shoppingCarts);
    }
}
