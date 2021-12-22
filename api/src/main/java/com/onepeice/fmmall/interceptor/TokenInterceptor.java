package com.onepeice.fmmall.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //解决浏览器的预检模式：发送正式请求前会发送预检请求来判断网络是否通畅，此时默认的方法是OPTIONS，所以此时不能做拦截
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)){
            return true;
        }

//        String token = request.getParameter("token");
        //将token放到请求投header中传递到后端，所以要通过getHeader方法纳token
        String token = request.getHeader("token");
        if (token == null) {
            ResultVo resultVo = new ResultVo(ResStatus.LOGIN_FAIL_NOT, "请登录", null);
            doResponse(response,resultVo);
        }else {
           try{
               JwtParser parser = Jwts.parser();
               parser.setSigningKey("QIANfeng6666");//解析token的signingkey必须和生成token时设置的密码一样
               //如果token正确（密码正确，有效期内）则正常执行，否则抛出异常
               Jws<Claims> claimsJws = parser.parseClaimsJws(token);
               return true;
           }catch (ExpiredJwtException e){
               doResponse(response,new ResultVo(ResStatus.LOGIN_FAIL_OVERDUE,"请重新登陆",null));
           }catch (UnsupportedJwtException e){
               doResponse(response,new ResultVo(ResStatus.NO,"非法token，开启守护模式",null));
           }
        }
        return false;

    }

    private void doResponse(HttpServletResponse response,ResultVo resultVo) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String s = new ObjectMapper().writeValueAsString(resultVo);
        writer.println(s);
        writer.flush();
        writer.close();
    }
}
