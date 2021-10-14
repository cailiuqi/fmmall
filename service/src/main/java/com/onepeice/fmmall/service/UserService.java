package com.onepeice.fmmall.service;

import com.onepeice.fmmall.entity.Users;
import com.onepeice.fmmall.vo.ResultVo;


public interface UserService {

    public ResultVo checkLogin(String name , String pwd);
    public ResultVo regist(Users user);
}
