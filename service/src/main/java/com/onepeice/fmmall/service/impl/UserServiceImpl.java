package com.onepeice.fmmall.service.impl;

import com.onepeice.fmmall.dao.UsersMapper;
import com.onepeice.fmmall.entity.Users;
import com.onepeice.fmmall.service.UserService;
import com.onepeice.fmmall.utils.Base64Utils;
import com.onepeice.fmmall.utils.MD5Utils;
import com.onepeice.fmmall.utils.TokenUtils;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import com.sun.istack.internal.NotNull;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public ResultVo checkLogin(String name, @NotNull String pwd) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",name);


        List<Users> user = usersMapper.selectByExample(example);
        if(user == null)
            return new ResultVo(400,"该用户不存在",null);
        else {
            if (pwd.equals(user.get(0).getPassword())){
                String token = TokenUtils.createTokenforJwt(user.get(0));
                ResStatus.TOKEN = token;
                return new ResultVo(10000, token,user.get(0));
            }else
            {
                return new ResultVo(400,"密码错误",null);
            }
        }
    }



    @Override
    @Transactional
    public ResultVo regist(Users user) {

        //根据用户查询看该用户是否已经被注册
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",user.getUsername());
        List<Users> users = usersMapper.selectByExample(example);

        //没有被注册则，执行注册
        if (users.size() == 0) {
            //密码进行md5加密
            String md5pwd = MD5Utils.md5(user.getPassword());
            user.setPassword(md5pwd);
            user.setUsername(user.getUsername());
            user.setUserImg("img/default.img");
            user.setUserRegtime(new Date());
            user.setUserModtime(new Date());
            int size = usersMapper.insert(user);
            if (size > 0) {
                return new ResultVo(10000,"注册成功，请登陆",null);
            }else {
                return new ResultVo(10000,"注册失败",null);
            }
        }else {
            return new ResultVo(10000,"用户已注册",null);

        }


    }
}
