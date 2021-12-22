package com.onepeice.fmmall.controller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.onepeice.fmmall.config.MyPayConfig;
import com.onepeice.fmmall.entity.Orders;
import com.onepeice.fmmall.service.OrderService;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Api(value = "提供订单相关操作接口",tags = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResultVo add(String cids, @RequestBody Orders order){
        Map<String, String> map = null;
        ResultVo resultVo = null;
        try {
            map = orderService.addOrder(cids, order);

            //如果订单保存成功，就进行微信支付请求
            if (map.get("orderId").toString()!=null){
                //save parameter of wxpay
                HashMap<String, String> data = new HashMap<>();
                data.put("body",map.get("producName"));
                data.put("total_fee","0.09");
                data.put("fee_type","CNY");
                data.put("trade_type","NATIVE");
                data.put("notify_url","/pay/success");
                data.put("out_trade_no",map.get("orderId"));

                //微信支付：申请支付链接
                //MyPayConfig存放的是商户信息
                WXPay wxPay = new WXPay(new MyPayConfig());
                Map<String, String> resp = wxPay.unifiedOrder(data);
                map.put("payUrl",resp.get("code_url"));
                System.out.println(resp);
            }else {
                resultVo = new ResultVo(ResStatus.NO,"提交订单失败",null);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resultVo = new ResultVo(ResStatus.NO,"提交订单失败",null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultVo;
    }
}
