package com.onepeice.fmmall.service.impl;

import com.onepeice.fmmall.dao.ShoppingCartMapper;
import com.onepeice.fmmall.dao.UsersMapper;
import com.onepeice.fmmall.entity.ShoppingCart;
import com.onepeice.fmmall.entity.ShoppingCartVO;
import com.onepeice.fmmall.entity.Users;
import com.onepeice.fmmall.service.ShopCarService;
import com.onepeice.fmmall.utils.Base64Utils;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class ShopCarServiceImpl implements ShopCarService {


    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public ResultVo getShopcar(String token) {
        //拦截器中已经对token进行来校验，所以service中无需验证token
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectAll();
        return new ResultVo(ResStatus.OK,"success",shoppingCarts);
    }

    @Override
    public ResultVo addShoppingCart(ShoppingCart cart) {
        cart.setCartTime(sdf.format(new Date()));
        int i = shoppingCartMapper.insert(cart);
        if(i>0){
            return new ResultVo(ResStatus.OK,"success",null);
        }else{
            return new ResultVo(ResStatus.NO,"111",null);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVo listShoppingCartsByUserId(int userid) {
        List<ShoppingCartVO> shoppingCartVOS = shoppingCartMapper.selectShopcartByUserId(userid);
        return new ResultVo(ResStatus.OK,"success",shoppingCartVOS);
    }

    @Override
    public ResultVo updateCartNum(int cartId, int cartNum) {
        int i = shoppingCartMapper.updateCartnumByCartid(cartId, cartNum);
        if(i>0){
            return new ResultVo(ResStatus.OK,"update success",null);
        }else{
            return new ResultVo(ResStatus.NO,"update fail",null);
        }
    }

    @Override
    public ResultVo listShoppingCartsByCids(String cids) {
        //使用tkmapper只能查询到某张表中拥有的字段，因此没法查询到商品名称、图片、单价等信息
        String[] arr = cids.split(",");
        List<Integer> cartIds = new ArrayList<>();
        for (int i=0; i<arr.length; i++){
            cartIds.add(Integer.parseInt(arr[i]));
        }
        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByCids(cartIds);
        ResultVo ResultVo = new ResultVo(ResStatus.OK, "success", list);
        return ResultVo;    }


}
