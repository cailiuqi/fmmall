package com.onepeice.fmmall.controller;

import com.onepeice.fmmall.entity.Users;
import com.onepeice.fmmall.service.UserService;
import com.onepeice.fmmall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "提供用户的登陆和注册接口",tags = "用户管理")
@CrossOrigin//解决跨域问题
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登陆接口")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(dataType = "String",name = "username",value = "登陆账号",required = true),
                    @ApiImplicitParam(dataType = "String",name = "password",value = "登陆密码",required = true)
            }
    )
    public ResultVo login(@RequestParam("username") String name , @RequestParam("password") String pwd){

        ResultVo resultVo = userService.checkLogin(name, pwd);
        return resultVo;


    }


    @ApiOperation("用户注册接口")
    @PostMapping("/regist")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(dataType = "String",name = "name",value = "注册账号",required = true),
                    @ApiImplicitParam(dataType = "String",name = "password",value = "注册密码",required = true)
            }
    )
    public ResultVo regist(@RequestParam String name , @RequestParam String pwd){

        Users user = new Users();
        user.setUsername(name);
        user.setPassword(pwd);
        ResultVo resultVo = userService.regist(user);
        return resultVo;
    }


    @PostMapping("/set")
    public ResultVo set(@RequestParam String iphone){
        System.out.println(iphone);
        ResultVo resultVo = new ResultVo();
        List<String> strings = new ArrayList<>();
        strings.add("test1");
        strings.add("test2");
        resultVo.setData(strings);
        return resultVo;
    }
}

