package com.onepeice.fmmall.service;

import com.onepeice.fmmall.entity.Orders;
import com.onepeice.fmmall.vo.ResultVo;

import java.sql.SQLException;
import java.util.Map;

public interface OrderService {
    public Map<String,String> addOrder(String cids, Orders order) throws SQLException;
    public int updateOrderStatus(String orderid,String status);
    public ResultVo gerOrderbyId(String orderid);
}
