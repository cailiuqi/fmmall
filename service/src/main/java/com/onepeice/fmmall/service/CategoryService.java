package com.onepeice.fmmall.service;
import com.onepeice.fmmall.vo.ResultVo;

public interface CategoryService {
    public ResultVo listCategories();

    public ResultVo listFirstLevelCategories();
}
