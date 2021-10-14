package com.onepeice.fmmall.dao;

import com.onepeice.fmmall.entity.IndexImg;
import com.onepeice.fmmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexImgMapper extends GeneralDAO<IndexImg> {

    List<IndexImg> listIndexImg();
}