package com.onepeice.fmmall.service.impl;

import com.onepeice.fmmall.dao.UserAddrMapper;
import com.onepeice.fmmall.entity.UserAddr;
import com.onepeice.fmmall.service.UserAddrService;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class UserAddrServiceImpl implements UserAddrService {
    @Autowired
    private UserAddrMapper userAddrMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVo listAddrsByUid(int userId) {
        Example example = new Example(UserAddr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("status",1);
        ResultVo resultVo = null;
        List<UserAddr> userAddrs = userAddrMapper.selectByExample(example);
        if(userAddrs.size()>0){
            resultVo = new ResultVo(ResStatus.OK, "success", userAddrs);

        }else {
            resultVo = new ResultVo(ResStatus.NO, "userId:"+userId+"没有保存地址", userAddrs);
        }
        return resultVo;
    }
}
