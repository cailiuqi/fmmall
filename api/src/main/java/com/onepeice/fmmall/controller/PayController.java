package com.onepeice.fmmall.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.onepeice.fmmall.service.OrderService;
import com.onepeice.fmmall.vo.ResultVo;
import com.onepeice.fmmall.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/callback")
    public String paysuccess(HttpServletRequest request) throws Exception {

        //以流的形式读取微信返回的结果
        ServletInputStream inputStream = request.getInputStream();
        byte[] bytes = new byte[1024];
        int length = -1;
        StringBuilder stringBuilder = new StringBuilder();
        while ((  length = inputStream.read(bytes))!=-1){

            stringBuilder.append(new String(bytes,0,length));
        }
        System.out.println(stringBuilder.toString());
        Map<String, String> xmlToMap = WXPayUtil.xmlToMap(stringBuilder.toString());
        if (xmlToMap!=null&&"success".equalsIgnoreCase(xmlToMap.get("result_code"))){

            //1、支付成功

            //2、修改订单状态为""待发货已支付
            String orderId = xmlToMap.get("out_trade_no");
            int i = orderService.updateOrderStatus(orderId, "2");
            //3、通过websocket连接，向前端推送消息
            WebSocketServer.sendMsg(orderId,"1");
            //4、响应微信支付平台
            if(i>0){
                HashMap<String, String> resp = new HashMap<>();
                resp.put("return_code","success");
                resp.put("return_msg","OK");
                resp.put("appid",xmlToMap.get("appid"));
                resp.put("result_code","success");
                return WXPayUtil.mapToXml(resp);
            }
        }

        return null;
    }

    @GetMapping("/status/{oid}")
    public ResultVo getOrderStatus(@PathVariable("orderid") String oid,@RequestHeader("token") String token){

        ResultVo resultVo = orderService.gerOrderbyId(oid);
        return resultVo;
    }
}
