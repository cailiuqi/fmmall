package com.onepeice.fmmall.service.impl;


import com.onepeice.fmmall.dao.CategoryMapper;
import com.onepeice.fmmall.entity.CategoryVO;
import com.onepeice.fmmall.service.CategoryService;
import com.onepeice.fmmall.vo.ResStatus;
import com.onepeice.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询分类列表（包含三个级别的分类）
     * @return
     */
    public ResultVo listCategories() {
        List<CategoryVO> categoryVOS = categoryMapper.selectAllCategories();
        ResultVo ResultVo = new ResultVo(ResStatus.OK, "success", categoryVOS);
        return ResultVo;
    }

    /**
     * 查询所有一级分类，同时查询当前一级分类下销量最高的6个商品
     * @return
     */
    public ResultVo listFirstLevelCategories() {
        List<CategoryVO> categoryVOS = categoryMapper.selectFirstLevelCategories();
        ResultVo ResultVo = new ResultVo(ResStatus.OK, "success", categoryVOS);
        return ResultVo;
    }

}
