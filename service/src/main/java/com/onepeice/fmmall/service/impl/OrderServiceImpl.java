package com.onepeice.fmmall.service.impl;

import com.onepeice.fmmall.dao.OrderItemMapper;
import com.onepeice.fmmall.dao.OrdersMapper;
import com.onepeice.fmmall.dao.ProductSkuMapper;
import com.onepeice.fmmall.dao.ShoppingCartMapper;
import com.onepeice.fmmall.entity.*;
import com.onepeice.fmmall.service.OrderService;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public  Map<String,String>  addOrder(String cids, Orders order) throws SQLException {

        HashMap<String, String> map = new HashMap<>();
        //1.根据cids查询当前订单中关联的购物车记录详情（包括库存）
        String[] arr = cids.split(",");
        List<Integer> cidsList = new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            cidsList.add(Integer.parseInt(arr[i]));
        }
        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByCids(cidsList);

        //2.校验库存
        boolean f = true;
        String untitled = "";
        for (ShoppingCartVO sc: list) {
            if(Integer.parseInt(sc.getCartNum())> sc.getSkuStock()){
                f = false;
            }
            //获取所有商品名称，以,分割拼接成字符串
            untitled = untitled+sc.getProductName()+",";
        }

        if(f){
            //3. 表示库存充足----保存订单
            //a.userId
            //b.untitled
            //c.收货人信息：姓名、电话、地址
            //d.总价格
            //e.支付方式
            //f.订单创建时间
            order.setUntitled(untitled);
            order.setCreateTime(new Date());
            order.setStatus(ResStatus.ORDER);

            //生成订单编号
            String orderId = UUID.randomUUID().toString().replace("-", "");
            order.setOrderId(orderId);
            //保存订单
            int i = ordersMapper.insert(order);

            //生成商品快照
            for (ShoppingCartVO sc: list) {
                int cnum = Integer.parseInt(sc.getCartNum());
                String itemId = System.currentTimeMillis()+""+ (new Random().nextInt(89999)+10000);
                OrderItem orderItem = new OrderItem(itemId, orderId, sc.getProductId(), sc.getProductName(), sc.getProductImg(), sc.getSkuId(), sc.getSkuName(), new BigDecimal(sc.getSellPrice()), cnum, new BigDecimal(sc.getSellPrice() * cnum), new Date(), new Date(), 0);
                orderItemMapper.insert(orderItem);
            }


            //扣减库存:根据套餐id修改套餐库存量
            for (ShoppingCartVO sc: list) {
                String skuId = sc.getSkuId();
                int newStock = sc.getSkuStock() - Integer.parseInt(sc.getCartNum());
                ProductSku productSku = new ProductSku();
                productSku.setSkuId(skuId);
                productSku.setStock(newStock);
                productSkuMapper.updateByPrimaryKeySelective(productSku);
            }
            //删除购物车
            for (int cid : cidsList){
                shoppingCartMapper.deleteByPrimaryKey(cid);
            }

            map.put("orderId",orderId);
            map.put("producName",untitled);
            return map;
        }else{
            //表示库存不足
            return null;
        }
    }

    @Override
    public int updateOrderStatus(String orderid, String status) {
        Orders orders = new Orders();
        orders.setOrderId(orderid);
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public ResultVo gerOrderbyId(String orderid) {
//        Example example = new Example(Orders.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo(orderid);
        Orders order = ordersMapper.selectByPrimaryKey(orderid);
        return new ResultVo(ResStatus.OK,"success",order.getOrderId());
    }
}
