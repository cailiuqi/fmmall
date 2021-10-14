package com.onepeice.fmmall.service.impl;

import com.onepeice.fmmall.dao.IndexImgMapper;
import com.onepeice.fmmall.entity.IndexImg;
import com.onepeice.fmmall.service.IndexImgService;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndexImgServiceImpl implements IndexImgService {

    @Resource
    private IndexImgMapper indexImgMapper;

    @Override
    public ResultVo listIndex() {
        List<IndexImg> indexImgs = indexImgMapper.listIndexImg();
        if (indexImgs == null||indexImgs.size()==0) {
            return new ResultVo(ResStatus.NO,"fail",null);
        }else {
            return new ResultVo(ResStatus.OK,"success",indexImgs);
        }
    }
}
